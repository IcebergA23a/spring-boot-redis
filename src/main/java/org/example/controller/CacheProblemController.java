package org.example.controller;

import org.example.service.CacheProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc 缓存问题：缓存击穿、缓存穿透、缓存血崩怎么解决
 * @Author bingshan
 * @Date 2025/12/14 14:18
 */
@RestController
@RequestMapping("/cacheProblem")
public class CacheProblemController {

    @Autowired
    private CacheProblemService cacheProblemService;

    @GetMapping("/cacheBreakdown/{key}")
    public  String cacheBreakdown(@PathVariable String key) {
        return cacheProblemService.cacheBreakdown(key);
    }

    @GetMapping("/cachePenetration/{key}")
    public String cachePenetration(@PathVariable String key) {
        return cacheProblemService.cachePenetration(key);
    }

}
