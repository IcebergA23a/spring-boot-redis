package org.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

/**
 * @Desc Redis util
 * @Author bingshan
 * @Date 2025/12/14 15:49
 */
@Component
public class RedisUtil {

    //  --  unlock key
    private static final String UNLOCK = """
            if (redis.call('exists', KEYS[1]) == 0) then
                -- 锁不存在，直接返回
                return nil;
            end 
            local currentValue = redis.call('get', KEYS[1])
            if currentValue == ARGV[1] then
                -- 是自己的锁，删除
                return redis.call('del', KEYS[1])
            end        
            return 0;
            """;
    private static final DefaultRedisScript<Long> unlockScript = new DefaultRedisScript<>(UNLOCK, Long.class);

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * Unlock
     * @param key
     * @param value
     * @return
     */
    public Boolean unlock(String key, String value) {
        Long result = (Long) redisTemplate.execute(
                unlockScript,
                Collections.singletonList(key),
                value
        );
        return result != null && result > 0;
    }
}
