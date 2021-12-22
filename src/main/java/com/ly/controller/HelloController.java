package com.ly.controller;

import com.ly.servise.ISay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @Autowired
    private ISay iSay;
    @GetMapping("hello")
    public String hello(){
        log.info(iSay.sayWord());
        return iSay.sayWord();
    }
}
