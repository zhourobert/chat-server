package com.robertZhou.chat.websocket;

import com.robertZhou.chat.websocket.handler.MsgOutChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * websocket服务端
 */
@Component
@Slf4j
public class NettyWsServer {

    @Value("${server.ws.port}")
    private Integer port;

    @Autowired
    private List<SimpleChannelInboundHandler> inChannelHandlers;

    /**
     * 启动websocket服务
     */
    public void initWsServer(){
        //主从线程池
        EventLoopGroup master = new NioEventLoopGroup();
        EventLoopGroup slave = new NioEventLoopGroup();

        //启动服务
        try {
            new ServerBootstrap()
                    .group(master, slave)
                    .channel(NioServerSocketChannel.class)
                    //消息处理器 - 责任处理器链
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            //管道
                            ChannelPipeline pipeline = ch.pipeline();
                            //对Http协议的支持
                            pipeline.addLast(new HttpRequestDecoder());
                            pipeline.addLast(new HttpResponseEncoder());
                            pipeline.addLast(new HttpObjectAggregator(1024 * 1024 * 10));
                            //对WebSocket协议的支持  HttpFullRequest
                            //WebSocketServerProtocolHandler
                            // - 处理升级握手请求
                            // - 处理ping, pong, close等消息
                            pipeline.addLast(new WebSocketServerProtocolHandler("/im"));

                            //出站消息处理器
                            pipeline.addLast(new MsgOutChannelHandler());

                            //入站消息处理器
                            if (!CollectionUtils.isEmpty(inChannelHandlers)) {
                                for (SimpleChannelInboundHandler inChannelHandler : inChannelHandlers) {
                                    //创建一个自定义的消息处理器，处理数据帧（文本帧、二进制帧）
                                    pipeline.addLast(inChannelHandler);
                                }
                            }
                        }
                    })
                    .bind(port)
                    .sync();
            log.info("websocket服务启动成功! - {}", port);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
