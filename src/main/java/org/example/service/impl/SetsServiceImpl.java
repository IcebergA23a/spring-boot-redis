package org.example.service.impl;

import org.example.service.SetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author bingshan
 * @since 2026/1/4 19:02
 */
@Service
public class SetsServiceImpl implements SetsService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加好友
     * @param userName
     * @param friendNameList
     * @return
     */
    @Override
    public Long addGoodFriend(String userName, List<String> friendNameList) {
        return redisTemplate.opsForSet().add("user:" + userName, friendNameList.toArray());
    }

    /**
     * 获取共同好友
     * @param userName1
     * @param userName2
     * @return
     */
    @Override
    public Set<String> getCommonFriends(String userName1, String userName2) {
        redisTemplate.opsForSet().intersectAndStore("user:" + userName1,
                "user:" + userName2,
                "user:common:" + userName1 + ":" + userName2);
        Set<String> result = redisTemplate.opsForSet().members("user:common:" + userName1 + ":" + userName2);
        return result;
    }
}
