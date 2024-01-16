package com.qf.chat.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.chat.commons.base.BaseController;
import com.qf.chat.commons.returnresult.Resp;
import com.qf.chat.entity.FriendRequest;
import com.qf.chat.service.FriendRequestService;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.util.List;

/**
 * 好友申请表(FriendRequest)表控制层
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@RestController
@RequestMapping("/friendRequest")
public class FriendRequestController extends BaseController<FriendRequestService> {
    @PostMapping("/sendFriendRequest")
    public Resp sendFriendRequest(@RequestBody FriendRequest friendRequest){
        boolean result=getService().sendFriendRequest(friendRequest);
    }
}

