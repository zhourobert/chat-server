package com.qf.chat.commons.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    //创建时间
    private Date createTime=new Date();
    //最后修改时间
    private Date updateTime=new Date();
    //状态 0-普通好友 1-拉黑
    private Integer status=0;
    //删除标识
    private Integer delFlag=0;
}
