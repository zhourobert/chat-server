package com.qf.chat.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.chat.commons.base.BaseController;
import com.qf.chat.entity.FriendRelation;
import com.qf.chat.service.FriendRelationService;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.util.List;

/**
 * 好友关系(FriendRelation)表控制层
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@RestController
@RequestMapping("friendRelation")
public class FriendRelationController extends BaseController<FriendRelationService> {
    
}

