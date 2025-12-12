package org.example.controller;

import org.example.request.BloomFilterRequest;
import org.example.service.BloomFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc BloomFilter
 * @Desc  https://redisson.pro/docs/data-and-services/objects/#bloom-filter
 * @Author bingshan
 * @Date 2025/12/12 10:59
 */
@RestController
@RequestMapping("/bloomFilter")
public class BloomFilterController {

    @Autowired
    BloomFilterService bloomFilterService;

   @PostMapping("/addBloomFilter")
    public Boolean addBloomFilter(@RequestBody BloomFilterRequest request) {
        return bloomFilterService.addToBloomFilter(request);
    }

    @GetMapping("/mightContain/{key}/{value}")
    public Boolean mightContain(@PathVariable String key, @PathVariable String value) {
        return bloomFilterService.mightContain(key, value);
    }

    @GetMapping("/bloomFilterCount/{key}")
    public long bloomFilterCount(@PathVariable String key) {
        return bloomFilterService.bloomFilterCount(key);
    }
}
