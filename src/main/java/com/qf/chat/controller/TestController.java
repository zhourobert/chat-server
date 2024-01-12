package com.qf.chat.controller;

import com.qf.chat.commons.returnresult.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @GetMapping("/t")
    public Resp test(){
        log.error("链接通畅");
        return Resp.succ();
    }
}
