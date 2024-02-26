package com.robertZhou.chat.websocket.entity;

import lombok.Data;

@Data
public class Message {
    private Integer id;//当前消息的id号
    private Integer action;
    private Integer from;
    private Integer to;
    private Integer type; //1-文本 2-图片
    private String content;
}
