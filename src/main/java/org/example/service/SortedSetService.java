package org.example.service;

import org.example.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface SortedSetService {

    boolean addPlayers(List<Player> playerList);

    HashMap<String, Double> getTop3Player();

    Long getPlayerRank(Integer playerId);
}
