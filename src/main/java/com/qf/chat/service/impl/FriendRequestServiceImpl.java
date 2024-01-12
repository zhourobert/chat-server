package com.qf.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.chat.dao.FriendRequestDao;
import com.qf.chat.entity.FriendRequest;
import com.qf.chat.service.FriendRequestService;
import org.springframework.stereotype.Service;

/**
 * 好友申请表(FriendRequest)表服务实现类
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@Service("friendRequestService")
public class FriendRequestServiceImpl extends ServiceImpl<FriendRequestDao, FriendRequest> implements FriendRequestService {

}

