package com.qf.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.chat.dao.FriendRequestDao;
import com.qf.chat.entity.FriendRequest;
import com.qf.chat.service.FriendRelationService;
import com.qf.chat.service.FriendRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    @Override
    public void sendFriendRequest(FriendRequest friendRequest) {

        //查询是否有这个请求数据
        FriendRequest request = query().eq("u_id", friendRequest.getUId())
                .eq("f_id", friendRequest.getFId())
                .one();

        System.out.println("111111;"+friendRequest.getNotes());
        if (request!=null){//如果有则更新这一条数据
            update().set("notes",friendRequest.getNotes())
                    .set("request_msg",friendRequest.getRequestMsg())
                    .eq("u_id", friendRequest.getUId())
                    .eq("f_id", friendRequest.getFId())
                    .update();
            friendRelationService.update().set("notes",friendRequest.getNotes())
                    .eq("u_id", friendRequest.getUId())
                    .eq("f_id", friendRequest.getFId())
                    .update();
        }else {//如果无则新建一条数据
            save(friendRequest);
            friendRelationService.save(friendRequest.getRelation());
        }
    }

    @Override
    public List<FriendRequest> getFriendRequestList(Integer uid) {
        return getBaseMapper().queryMyRequest(uid);

    }
}

