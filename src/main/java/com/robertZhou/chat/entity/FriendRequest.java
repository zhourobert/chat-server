package com.robertZhou.chat.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robertZhou.chat.commons.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 好友申请表(FriendRequest)表实体类
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest extends BaseEntity {

//申请人id
    @NotNull
    @JsonProperty
    private Integer uId;
//被申请人id
    @NotNull
    @JsonProperty
    private Integer fId;
//申请人对被申请人的备注
    private String notes;
//验证消息
    private String requestMsg;
    @TableField(exist = false)
    private User friendInfo;


    public FriendRequest exchange(){
        return new FriendRequest(this.fId,this.uId, this.notes,this.requestMsg,this.friendInfo);
    }
    public FriendRelation getRelation(){
        return new FriendRelation(this.uId,this.fId,this.notes);
    }





}

