package org.example.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Desc TODO
 * @Author bingshan
 * @Date 2025/12/5 21:46
 */
public class TimeUtil {
    // 定义一个基准时间点，使用LocalDateTime的最大值转换为时间戳
    private static final long BASE_TIME =
            LocalDateTime.parse("2100-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli();

    /**
     * 将字符串转换为时间戳
     * @param dateTimeStr 格式为"yyyy-MM-dd HH:mm:ss"的时间字符串
     * @return 时间戳  the number of milliseconds since the epoch of 1970-01-01T00:00:00Z
     */
    public static long stringDateTimeToLong(String dateTimeStr) {
        // 使用DateTimeFormatter
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dateTimeStr, dtf);
        Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
        long timestamp = instant.toEpochMilli();
        return timestamp;
    }

    /**
     * 将DateTime转换为时间戳
     * @param dateTime 格式为"yyyy-MM-dd HH:mm:ss"的时间
     * @return 时间戳  the number of milliseconds since the epoch of 1970-01-01T00:00:00Z
     */
    public static long dateTimeToLong(LocalDateTime dateTime) {
        Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        long timestamp = instant.toEpochMilli();
        return timestamp;
    }

    /**
     * 计算玩家分数
     *      时间排序值 = (基准时间 - 玩家得分时间) / 基准时间
     *      score = 玩家得分 + 时间排序值
     * @param playerScore 玩家得分
     * @param playerScoreTime 玩家得分时间戳
     * @return 计算后的分数
     */
    public static double calsScore(int playerScore, long playerScoreTime) {
        return playerScore + (BASE_TIME - playerScoreTime) * 1.0 / BASE_TIME;
    }
}
