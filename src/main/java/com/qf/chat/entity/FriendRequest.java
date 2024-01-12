package com.qf.chat.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.qf.chat.commons.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 好友申请表(FriendRequest)表实体类
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class FriendRequest extends BaseEntity {

//申请人id
    private Integer uId;
//被申请人id
    private Integer fId;
//申请人对被申请人的备注
    private String notes;
//验证消息
    private String requestMsg;





}

