package org.example.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.example.entity.Player;
import org.example.service.SortedSetService;
import org.example.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Desc TODO
 * @Author bingshan
 * @Date 2025/12/5 19:45
 */

@Service
public class SortedSetServiceImpl implements SortedSetService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增玩家分数
     *     分数越大，排名越靠前
     *     时间戳越小，排名越靠前
     * @param playerList
     * @return
     */
    @Override
    public boolean addPlayers(List<Player> playerList) {
        for (Player player : playerList) {
            long playerScoreTime = TimeUtil.dateTimeToLong(player.getPlayerScoreTime());
            double score = TimeUtil.calsScore(player.getPlayerScore(), playerScoreTime);
            redisTemplate.opsForZSet().add("leaderboard:339", "player:" + player.getPlayerId(), score);
        }
        return true;
    }

    /**
     * 获取前三名玩家
     * @return
     */
    @Override
    public HashMap<String, Double> getTop3Player() {
        HashMap<String, Double> result = new HashMap<>();
        Set<ZSetOperations.TypedTuple<String>> playerSet = redisTemplate.opsForZSet().reverseRangeWithScores("leaderboard:339", 0, 2);
        for (ZSetOperations.TypedTuple<String> player : playerSet) {
            result.put(player.getValue(), player.getScore());
        }
        return result;
    }

    /**
     * 获取玩家排名
     * @param playerId
     * @return
     */
    @Override
    public Long getPlayerRank(Integer playerId) {
        Long rank = redisTemplate.opsForZSet().reverseRank("leaderboard:339", "player:" + playerId);
        return rank;
    }
}
