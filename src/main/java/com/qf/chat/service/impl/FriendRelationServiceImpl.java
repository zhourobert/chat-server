package com.qf.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.chat.dao.FriendRelationDao;
import com.qf.chat.entity.FriendRelation;
import com.qf.chat.service.FriendRelationService;
import org.springframework.stereotype.Service;

/**
 * 好友关系(FriendRelation)表服务实现类
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@Service("friendRelationService")
public class FriendRelationServiceImpl extends ServiceImpl<FriendRelationDao, FriendRelation> implements FriendRelationService {

}

