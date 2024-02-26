package com.robertZhou.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robertZhou.chat.dao.FriendRelationDao;
import com.robertZhou.chat.entity.FriendRelation;
import com.robertZhou.chat.service.FriendRelationService;
import org.springframework.stereotype.Service;

/**
 * 好友关系(FriendRelation)表服务实现类
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@Service("friendRelationService")
public class FriendRelationServiceImpl extends ServiceImpl<FriendRelationDao, FriendRelation> implements FriendRelationService {
    @Override
    public void beFriending(Integer uid,Integer fid,Integer code){
        //先检查数据库是否有这两条数据,u2f为用户对朋友，f2u为朋友对用户
        FriendRelation u2f = query().eq("u_id", uid)
                .eq("f_id", fid).one();
        FriendRelation f2u = query().eq("u_id", fid)
                .eq("f_id", uid).one();
        //你对好友的关系数据
        if (u2f != null) {
            update().set("status", code)
                    .eq("u_id", uid)
                    .eq("f_id", fid).update();
        } else {
            save((FriendRelation) new FriendRelation().setUId(uid).setFId(fid).setStatus(code));
        }
        //好友对你的关系数据
        if (f2u != null) {
            update().set("status", code)
                    .eq("u_id", fid)
                    .eq("f_id", uid).update();
        } else {
            save((FriendRelation) new FriendRelation().setUId(fid).setFId(uid).setStatus(code));
        }
    }
}

