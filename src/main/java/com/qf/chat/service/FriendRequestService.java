package com.qf.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.chat.entity.FriendRequest;
import com.qf.chat.entity.vo.HandleRequestVo;

import java.util.List;

/**
 * 好友申请表(FriendRequest)表服务接口
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
public interface FriendRequestService extends IService<FriendRequest> {

    void sendFriendRequest(FriendRequest friendRequest);

    List<FriendRequest> getFriendRequestList(Integer uid);

    void handleRequest(HandleRequestVo handleRequestVo);
}

