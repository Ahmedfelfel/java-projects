package com.felfel.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String greating(HttpServletRequest http)
    {
        String sessionId = http.getSession().getId();
        return "hi \n session Id : "+ sessionId;
    }


}
