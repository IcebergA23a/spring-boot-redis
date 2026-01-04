package org.example.controller;

import org.example.service.SetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Sets 结构
 * @author bingshan
 * @since 2026/1/4 19:01
 */
@RestController
@RequestMapping("/sets")
public class SetsController {
    @Autowired
    private SetsService setsService;

    /**
     * 添加好友
     * @param userName
     * @param friendNameList
     * @return
     */
    @PostMapping("/addGoodFriend")
    public Long addGoodFriend(@RequestParam String userName, @RequestBody List<String> friendNameList) {
        return setsService.addGoodFriend(userName, friendNameList);
    }

    /**
     * 获取共同好友
     * @param userName1
     * @param userName2
     * @return
     */
    @GetMapping("/getCommonFriends")
    public Set<String> getCommonFriends(@RequestParam String userName1, @RequestParam String userName2) {
        return setsService.getCommonFriends(userName1, userName2);
    }
}
