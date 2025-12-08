package org.example.controller;

import org.example.entity.Player;
import org.example.entity.UserSign;
import org.example.service.BitmapService;
import org.example.service.SortedSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc Bitmap 结构
 * @Author bingshan
 * @Date 2025/12/5 19:44
 */
@RestController
@RequestMapping("/bitmap")
public class BitmapController {

    @Autowired
    private BitmapService bitmapService;

    /**
     * 用户登录
     * @param userId
     * @return
     */
    @PostMapping("/userLogin/{userId}")
    public Boolean userLogin(@PathVariable Integer userId) {
        return bitmapService.userLogin(userId);
    }

    /**
     * 用户是否登录
     * @param userId
     * @return
     */
    @GetMapping("/userIsLogin/{userId}")
    public Boolean userIsLogin(@PathVariable Integer userId) {
        return bitmapService.userIsLogin(userId);
    }

    /**
     * 用户登出
     * @param userId
     * @return
     */
    @PostMapping("/userLogout/{userId}")
    public Boolean userLogout(@PathVariable Integer userId) {
        return bitmapService.userLogout(userId);
    }

    /**
     * 用户签到
     * @param userId
     * @param userSign
     * @return
     */
    @PostMapping("/userSign/{userId}")
    public Boolean userSign(@PathVariable Integer userId, @RequestBody UserSign userSign) {
        return bitmapService.userSign(userId, userSign);
    }

    /**
     * 用户是否签到
     * @param userId
     * @param userSign
     * @return
     */
    @PostMapping("/userIsSign/{userId}")
    public Boolean userIsSign(@PathVariable Integer userId, @RequestBody UserSign userSign) {
        return bitmapService.userIsSign(userId, userSign);
    }

    /**
     * 统计用户当月签到次数
     * @param userId
     * @param yearMouth
     * @return
     */
    @GetMapping("/countUserSign/{userId}/{yearMouth}")
    public Long countUserSign(@PathVariable Integer userId, @PathVariable String yearMouth) {
        return bitmapService.countUserSign(userId, yearMouth);
    }
 }
