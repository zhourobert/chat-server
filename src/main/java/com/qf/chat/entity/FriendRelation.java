package com.qf.chat.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.qf.chat.commons.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 好友关系(FriendRelation)表实体类
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class FriendRelation  extends BaseEntity {

//用户id
    private Integer uId;
//好友id
    private Integer fId;
//当前用户对好友的备注信息
    private String notes;





}

