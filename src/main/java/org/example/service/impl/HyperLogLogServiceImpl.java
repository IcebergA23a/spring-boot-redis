package org.example.service.impl;

import org.example.service.HyperLogLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author bingshan
 * @since 2026/1/12 20:10
 */
@Service
public class HyperLogLogServiceImpl implements HyperLogLogService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Long pfAdd(String key, String value) {
        return redisTemplate.opsForHyperLogLog().add(key, value);
    }

    @Override
    public Long pfCount(String key) {
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    @Override
    public Long pfMerge(String destKey, String... sourceKeys) {
        return redisTemplate.opsForHyperLogLog().union(destKey, sourceKeys);
    }
}
