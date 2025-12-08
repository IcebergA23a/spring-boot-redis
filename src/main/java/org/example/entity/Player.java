package org.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Desc TODO
 * @Author bingshan
 * @Date 2025/12/5 20:18
 */
public class Player {

    int playerId;
    int playerScore;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime playerScoreTime;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }




    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public LocalDateTime getPlayerScoreTime() {
        return playerScoreTime;
    }

    public void setPlayerScoreTime(LocalDateTime playerScoreTime) {
        this.playerScoreTime = playerScoreTime;
    }



}
