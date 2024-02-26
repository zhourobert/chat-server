package com.robertZhou.chat.websocket.handler;

import cn.hutool.json.JSONUtil;
import com.robertZhou.chat.websocket.entity.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
//标记当前的消息处理器是共享的
@ChannelHandler.Sharable
@Slf4j
//这个ChannelHandler必须是处理器的第一个
@Order(0)
public class InitMsgChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有一个客户端连接成功！");
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有一个客户端断开了连接!");
        ctx.fireChannelInactive();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        log.debug("接收到客户端的消息 - {}", textWebSocketFrame.text());

        //将消息的json转换成Message对象
        Message message = JSONUtil.toBean(textWebSocketFrame.text(), Message.class);
        //将Message对象往后传递
        ctx.fireChannelRead(message);
    }
}
