package com.robertZhou.chat.websocket.handler;

import cn.hutool.json.JSONUtil;
import com.robertZhou.chat.websocket.entity.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 出站消息处理器 - 专门处理Message类型的消息
 */
public class MsgOutChannelHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Message) {
            msg = new TextWebSocketFrame(JSONUtil.toJsonStr(msg));
        }
        super.write(ctx, msg, promise);
    }
}
