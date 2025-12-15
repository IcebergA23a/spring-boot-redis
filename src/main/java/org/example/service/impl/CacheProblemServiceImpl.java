package org.example.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.example.service.CacheProblemService;
import org.example.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Desc 缓存问题
 * @Author bingshan
 * @Date 2025/12/14 14:24
 */
@Service
  public class CacheProblemServiceImpl implements CacheProblemService {
    private static final Logger logger = LoggerFactory.getLogger(CacheProblemServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 缓存击穿：使用锁
     * @param key
     * @return
     */
    @Override
    public String cacheBreakdown(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        // redis缓存中不存在数据
        if (StringUtils.isEmpty(value)) {
            // 加锁
            String uuid = UUID.randomUUID().toString();
            try {
                // 加锁的逻辑应该写到try{}里
                Boolean lock = redisTemplate.opsForValue().setIfAbsent("LOCK:" + key, uuid, 30, TimeUnit.SECONDS);
                // 如果 lock == null  if(lock) 异常
                if (Boolean.TRUE.equals(lock)) {
                    // 从数据库中获取数据
                    String result = getDataFromMysql(key);
                    // 放入缓存
                    if (StringUtils.isNotEmpty(result)) {
                        redisTemplate.opsForValue().set(key, result, 60 * 60 * 24, TimeUnit.SECONDS);
                    }
                    return result;
                } else {
                    // 加锁失败, 暂停100ms后, 重新获取缓存
                    try {
                        logger.info("击穿--threadId={}, 缓存未命中, 睡眠100millis,  等待重试 key={}", Thread.currentThread().getName(), key);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return cacheBreakdown(key);
                }
            } catch (Exception ex) {

            } finally {
                // 释放锁
                redisUtil.unlock("LOCK:" + key, uuid);
            }
        }
        logger.info("击穿--threadId={},缓存命中, key={}", Thread.currentThread().getName(), key);
        return value;
    }

    /**
     * 缓存穿透: 缓存空值
     * @param key
     * @return
     */
    @Override
    public String cachePenetration(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);

        if (StringUtils.isNotEmpty(value)) {
            // 1、redis缓存中存在数据
            logger.info("穿透--threadId={},缓存命中, key={}", Thread.currentThread().getName(), key);
            return value;
        } else {
            // 2、从数据库中获取数据
            String result = getDataFromMysqlAndReturnNull(key);
            // 3、放入缓存
            if (StringUtils.isNotEmpty(result)) {
                logger.info("穿透--threadId={},数据库中存在数据, key={}", Thread.currentThread().getName(), key);
                redisTemplate.opsForValue().set(key, result, 60 * 60 * 24, TimeUnit.SECONDS);
            } else {
                // 4、数据库中不存在该数据, 缓存空值,并设置一个较短的过期时间, 防止缓存穿透
                logger.info("穿透--threadId={},数据库中不存在数据, 放一个null值, key={}", Thread.currentThread().getName(), key);
                Random random = new Random(10);
                int randomSec = random.nextInt(60);
                redisTemplate.opsForValue().set(key, "null", 60 + randomSec, TimeUnit.SECONDS);
            }
            return result;
        }
    }

    private String getDataFromMysql(String key) {
        logger.info("击穿--threadId={}, 从数据库读取数据, key={}", Thread.currentThread().getName(), key);
        String value = "MySQL store value";
        return value;
    }

    private String getDataFromMysqlAndReturnNull(String key) {
        logger.info("穿透--threadId={}, 从数据库读取数据,数据不存在, 返回null, key={}", Thread.currentThread().getName(), key);
        return null;
    }
}
