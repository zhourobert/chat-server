package com.robertZhou.chat.websocket.base;

import com.robertZhou.chat.websocket.entity.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 模板类 - 定义模板方法
 *
 * 父类决定了模板方法的触发时机
 * 子类决定了模板方法具体实现
 */
@ChannelHandler.Sharable
public abstract class BaseChannelhandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        if(message.getAction() == action()){
            handler(ctx, message);
        } else {
            ctx.fireChannelRead(message);
        }
    }

    protected abstract Integer action();

    protected abstract void handler(ChannelHandlerContext ctx, Message message);
}
