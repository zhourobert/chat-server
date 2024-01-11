package com.qf.controller;

import com.qf.commons.returnresult.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @PostMapping("/t")
    public Resp test(){
        log.error("测试error信息");
        return Resp.succ();
    }
}
