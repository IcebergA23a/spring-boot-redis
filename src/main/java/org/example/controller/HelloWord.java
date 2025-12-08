package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc TODO
 * @Author bingshan
 * @Date 2025/12/5 19:39
 */
@RestController
public class HelloWord {

    @GetMapping("/helloWord")
    public String helloWord() {
        return "Hello Word !";
    }
}
