package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * redisson 集群使用测试
 * @author bingshan
 * @since 2026/1/22 22:18
 */
@RestController
@RequestMapping("/cluster")
public class ClusterController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/getKey")
    public String getKey(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/setKey")
    public String setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "success";
    }
}
