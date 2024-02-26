package com.robertZhou.chat.application;

import com.robertZhou.chat.websocket.NettyWsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * SpringBoot启动后触发
 */
@Component
public class ApplicationRunning implements CommandLineRunner {

    @Autowired
    private NettyWsServer nettyWsServer;

    @Override
    public void run(String... args) throws Exception {
        //SpringBoot启动时运行
        nettyWsServer.initWsServer();
    }
}
