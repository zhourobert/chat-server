package com.qf.chat.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qf.chat.commons.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 历史消息表(MsgHistory)表实体类
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class MsgHistory  extends BaseEntity {

//发送者id
    @JsonProperty
    private Integer uId;
//接受者id
    @JsonProperty
    private Integer fId;
//消息的内容
    private String msg;
//消息的类型 0-文本消息 1-图片消息 2-语音消息 3-视频通话
    private Integer type;





}

