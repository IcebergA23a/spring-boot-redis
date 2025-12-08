package org.example.controller;

import org.example.entity.Player;
import org.example.service.SortedSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Desc Sorted Set结构
 * @Author bingshan
 * @Date 2025/12/5 19:44
 */
@RestController
@RequestMapping("/sortedSet")
public class SortedSetController {

    @Autowired
    private SortedSetService sortedSetService;

    /**
     * 新增玩家分数
     * @param playerList
     * @return
     */
    @PostMapping("/addPlayers")
    public boolean addPlayers(@RequestBody List<Player> playerList) {
        return sortedSetService.addPlayers(playerList);
    }

    /**
     * 获取前三名玩家
     * @return
     */
    @GetMapping("/getTop3Player")
    public HashMap<String, Double> getTop3Player(){
        return sortedSetService.getTop3Player();
    }

    /**
     * 获取玩家排名
     * @param playerId
     * @return
     */
    @GetMapping("/getPlayerRank/{playerId}")
    public Long getPlayerRank(@PathVariable Integer playerId){
        return sortedSetService.getPlayerRank(playerId);
    }
}
