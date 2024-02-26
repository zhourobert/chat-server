package com.robertZhou.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robertZhou.chat.commons.utils.JWTUtils;
import com.robertZhou.chat.dao.FriendRequestDao;
import com.robertZhou.chat.entity.FriendRequest;
import com.robertZhou.chat.entity.vo.HandleRequestVo;
import com.robertZhou.chat.service.FriendRelationService;
import com.robertZhou.chat.service.FriendRequestService;
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

        System.out.println("111111;" + friendRequest.getNotes());
        if (request != null) {//如果有则更新这一条数据
            update().set("notes", friendRequest.getNotes())
                    .set("request_msg", friendRequest.getRequestMsg())
                    .eq("u_id", friendRequest.getUId())
                    .eq("f_id", friendRequest.getFId())
                    .update();
            friendRelationService.update().set("notes", friendRequest.getNotes())
                    .eq("u_id", friendRequest.getUId())
                    .eq("f_id", friendRequest.getFId())
                    .update();
        } else {//如果无则新建一条数据
            save(friendRequest);
            friendRelationService.save(friendRequest.getRelation());
        }
    }

    @Override
    public List<FriendRequest> getFriendRequestList(Integer uid) {
        return getBaseMapper().queryFTMRequest(uid);

    }

    @Override
    public List<FriendRequest> getMyRequestList(Integer uid) {
        return getBaseMapper().queryMTFRequest(uid);
    }

    @Transactional
    @Override
    public void handleRequest(HandleRequestVo handleRequestVo) {
        update().set("status", handleRequestVo.getStatusCode())
                .eq("id", handleRequestVo.getFriendRequestId()).update();

        FriendRequest req = query().eq("id", handleRequestVo.getFriendRequestId()).one();
        Integer uid = JWTUtils.getId();
        Integer fid = req.getUId();
        //检查请求是同意还是拒绝
        Integer code = handleRequestVo.getStatusCode();
        friendRelationService.beFriending(uid, fid, code);
    }


}

