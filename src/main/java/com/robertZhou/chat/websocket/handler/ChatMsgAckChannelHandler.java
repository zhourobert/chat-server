package com.robertZhou.chat.websocket.handler;

import com.robertZhou.chat.service.MsgHistoryService;
import com.robertZhou.chat.websocket.base.BaseChannelhandler;
import com.robertZhou.chat.websocket.entity.Message;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMsgAckChannelHandler extends BaseChannelhandler {

    @Autowired
    private MsgHistoryService historyService;

    @Override
    protected Integer action() {
        return 102;
    }

    @Override
    protected void handler(ChannelHandlerContext ctx, Message message) {
        String mid = message.getContent();
        //修改当前消息的状态
        historyService.update()
                .set("status", 1)
                .eq("id", mid)
                .update();
    }
}
