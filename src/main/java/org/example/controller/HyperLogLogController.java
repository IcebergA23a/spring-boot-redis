package org.example.controller;

import org.example.service.HyperLogLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HyperLogLog 结构
 * @author bingshan
 * @since 2026/1/12 20:18
 */
@RestController
@RequestMapping("/hyperLogLog")
public class HyperLogLogController {
    @Autowired
    private HyperLogLogService hyperLogLogService;

    @GetMapping("/pfAdd")
    public Long pfAdd(String key, String value) {
        return hyperLogLogService.pfAdd(key, value);
    }

    @GetMapping("/pfCount")
    public Long pfCount(String key) {
        return hyperLogLogService.pfCount(key);
    }

    @GetMapping("/pfMerge")
    public Long pfMerge(String destKey, String... sourceKeys) {
        return hyperLogLogService.pfMerge(destKey, sourceKeys);
    }

}
