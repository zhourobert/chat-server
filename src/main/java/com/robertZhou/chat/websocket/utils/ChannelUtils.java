package com.robertZhou.chat.websocket.utils;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelUtils {

    private static Map<Integer, Channel> channelMap = new ConcurrentHashMap<>();

    public static void addChannel(Integer userId, Channel channel){
        channelMap.put(userId, channel);
    }

    public static Channel get(Integer uid){
        return channelMap.get(uid);
    }

    public static void remove(Integer uid){
        channelMap.remove(uid);
    }

    public static void remove(Channel channel) {
        for (Map.Entry<Integer, Channel> entry : channelMap.entrySet()) {
            if (entry.getValue().equals(channel)){
                channelMap.remove(entry.getKey());
                return;
            }
        }
    }
}
