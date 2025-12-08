package org.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Desc TODO
 * @Author bingshan
 * @Date 2025/12/6 20:24
 */
public class UserSign {

    private Integer userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime signDateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getSignDateTime() {
        return signDateTime;
    }

    public void setSignDateTime(LocalDateTime signDateTime) {
        this.signDateTime = signDateTime;
    }
}
