package com.robertZhou.chat.websocket.handler;

import com.robertZhou.chat.entity.MsgHistory;
import com.robertZhou.chat.service.MsgHistoryService;
import com.robertZhou.chat.websocket.base.BaseChannelhandler;
import com.robertZhou.chat.websocket.entity.Message;
import com.robertZhou.chat.websocket.utils.ChannelUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMsgChannelHandler extends BaseChannelhandler {

    @Autowired
    private MsgHistoryService msgHistoryService;

    @Override
    protected Integer action() {
        return 101;
    }

    @Override
    protected void handler(ChannelHandlerContext ctx, Message message) {
        Integer to = message.getTo();
        //通过对方的id找到对方的Channel
        Channel channel = ChannelUtils.get(to);
        //存储当前的历史消息 - 默认都是未读消息
        MsgHistory msgHistory = new MsgHistory()
                .setUId(message.getFrom())
                .setFId(message.getTo())
                .setType(message.getType())
                .setMsg(message.getContent());

        msgHistoryService.save(msgHistory); //主键回填

        //发送给客户端
        if (channel != null) {
            message.setId(msgHistory.getId());
            channel.writeAndFlush(message);
        }
    }
}
