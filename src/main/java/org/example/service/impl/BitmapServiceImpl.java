package org.example.service.impl;

import org.example.entity.Player;
import org.example.entity.UserSign;
import org.example.service.BitmapService;
import org.example.service.SortedSetService;
import org.example.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Desc TODO
 * @Author bingshan
 * @Date 2025/12/5 19:45
 */

@Service
public class BitmapServiceImpl implements BitmapService {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 用户登录
     * @param userId
     * @return
     */
    @Override
    public Boolean userLogin(Integer userId) {
        return redisTemplate.opsForValue().setBit("login:status", userId, true);
    }

    /**
     * 用户是否登录
     * @param userId
     * @return
     */
    @Override
    public Boolean userIsLogin(Integer userId) {
        return redisTemplate.opsForValue().getBit("login:status", userId);
    }

    /**
     * 用户登出
     * @param userId
     * @return
     */
    @Override
    public Boolean userLogout(Integer userId) {
        return redisTemplate.opsForValue().setBit("login:status", userId, false);
    }

    /**
     * 用户签到
     * @param userId
     * @param userSign
     * @return
     */
    @Override
    public Boolean userSign(Integer userId, UserSign userSign) {
        LocalDateTime ldt = userSign.getSignDateTime();

        int year = ldt.getYear();
        int month = ldt.getMonthValue();
        int day = ldt.getDayOfMonth();
        String key = "sign:" + userId + ":" + year + String.format("%02d",month);
        return redisTemplate.opsForValue().setBit(key, day - 1, true);
    }

    /**
     * 用户是否签到
     * @param userId
     * @param userSign
     * @return
     */
    @Override
    public Boolean userIsSign(Integer userId, UserSign userSign) {
        LocalDateTime ldt = userSign.getSignDateTime();
        int year = ldt.getYear();
        int month = ldt.getMonthValue();
        int day = ldt.getDayOfMonth();
        String key = "sign:" + userId + ":" + year + String.format("%02d",month);
        return redisTemplate.opsForValue().getBit(key, day - 1);

    }

    /**
     * 统计用户某月签到次数
     * @param userId
     * @param yearMouth
     * @return
     */
    @Override
    public Long countUserSign(Integer userId, String yearMouth) {
        String key = "sign:" + userId + ":" + yearMouth;
        return (Long) redisTemplate.execute((RedisConnection connection) -> connection.stringCommands().bitCount(key.getBytes()));

    }
}
