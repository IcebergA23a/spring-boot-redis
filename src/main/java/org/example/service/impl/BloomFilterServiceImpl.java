package org.example.service.impl;

import org.example.request.BloomFilterRequest;
import org.example.service.BloomFilterService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desc TODO
 * @Author bingshan
 * @Date 2025/12/12 14:04
 */
@Service
public class BloomFilterServiceImpl implements BloomFilterService {

    @Autowired
    private RedissonClient redissonClient;
    @Override
    public Boolean addToBloomFilter(BloomFilterRequest request) {
        String key = request.getKey();
        String[] values = request.getValue();
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(key);
        // Initialize if needed
        if (!bloomFilter.isExists()) {
            // expectedInsertions, falseProbability
            bloomFilter.tryInit(1000000, 0.03);
        }
        try {
            for (String val : values) {
                bloomFilter.add(val);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean mightContain(String key, String value) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(key);
        return bloomFilter.contains(value);
    }

    @Override
    public long bloomFilterCount(String key) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(key);
        return bloomFilter.count();
    }


}
