package com.robertZhou.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robertZhou.chat.entity.FriendRelation;

/**
 * 好友关系(FriendRelation)表服务接口
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
public interface FriendRelationService extends IService<FriendRelation> {
    void beFriending(Integer uid,Integer fid,Integer code);
}

