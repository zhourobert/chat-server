package com.robertZhou.chat.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.robertZhou.chat.commons.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 好友关系(FriendRelation)表实体类
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FriendRelation  extends BaseEntity {

//用户id
    @JsonProperty
    private Integer uId;
//好友id
    @JsonProperty
    private Integer fId;
//当前用户对好友的备注信息
    private String notes;





}

