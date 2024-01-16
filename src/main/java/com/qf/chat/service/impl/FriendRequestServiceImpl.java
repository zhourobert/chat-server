package com.qf.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.chat.dao.FriendRequestDao;
import com.qf.chat.entity.FriendRequest;
import com.qf.chat.service.FriendRelationService;
import com.qf.chat.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 好友申请表(FriendRequest)表服务实现类
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@Service("friendRequestService")
public class FriendRequestServiceImpl extends ServiceImpl<FriendRequestDao, FriendRequest> implements FriendRequestService {
    @Autowired
    private FriendRelationService friendRelationService;
    @Override
    public boolean sendFriendRequest(FriendRequest friendRequest) {

        //查询是否有这个请求数据
        FriendRequest request = query().eq("uId", friendRequest.getUId())
                .eq("fId", friendRequest.getFId())
                .one();
        if (request!=null){//如果有则更新这一条数据
            return update().set("notes",friendRequest.getNotes())
                    .set("requestMsg",friendRequest.getRequestMsg())
                    .eq("uId", friendRequest.getUId())
                    .eq("fId", friendRequest.getFId())
                    .update();
//            friendRelationService.update()
        }else {//如果无则新建一条数据
            return save(friendRequest);
        }
    }
}

