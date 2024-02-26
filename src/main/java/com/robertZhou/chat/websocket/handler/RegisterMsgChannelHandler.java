package com.robertZhou.chat.websocket.handler;

import com.robertZhou.chat.websocket.base.BaseChannelhandler;
import com.robertZhou.chat.websocket.entity.Message;
import com.robertZhou.chat.websocket.utils.ChannelUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

@Component
public class RegisterMsgChannelHandler extends BaseChannelhandler {

    @Override
    protected Integer action() {
        return 100;
    }

    @Override
    protected void handler(ChannelHandlerContext ctx, Message message) {
        //处理注册消息
        //获取用户id
        Integer uid = Integer.parseInt(message.getContent());
        //获取当前用户的Channel对象
        Channel channel = ctx.channel();
        //注册
        ChannelUtils.addChannel(uid, channel);
    }
}
