package com.qf.chat.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.chat.commons.base.BaseController;
import com.qf.chat.commons.returnresult.Resp;
import com.qf.chat.commons.returnresult.RespCode;
import com.qf.chat.commons.utils.JWTUtils;
import com.qf.chat.entity.FriendRequest;
import com.qf.chat.entity.vo.HandleRequestVo;
import com.qf.chat.service.FriendRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 好友申请表(FriendRequest)表控制层
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@RestController
@RequestMapping("/friendRequest")
@Slf4j
public class FriendRequestController extends BaseController<FriendRequestService> {
    @PostMapping("/sendFriendRequest")
    public Resp sendFriendRequest(@RequestBody @Validated FriendRequest friendRequest){
        log.debug("后端获取friendRequest：{}",friendRequest);

        if (!JWTUtils.verify(friendRequest.getUId())){
            return Resp.fail(RespCode.WRONG_TOKEN);
        }
        getService().sendFriendRequest(friendRequest);
        return Resp.succ();
    }

    @GetMapping("/getFriendRequest")
    public Resp getFriendRequest(){
        Integer uid=JWTUtils.getId();
        log.debug("后端进入getFriendRequest获取令牌uid：{}",uid);
//        List<FriendRequest> friendRequests = getService().query().eq("f_id", uid).list();
        List<FriendRequest> friendRequests = getService().getFriendRequestList(uid);
        return Resp.succ(friendRequests);
    }

    @PatchMapping ("/patchRequest")
    public Resp patchRequest(@RequestBody HandleRequestVo handleRequestVo){
        log.debug("后端进入patchRequest获取要修改的HandleRequestVo：{}",handleRequestVo);
        getService().handleRequest(handleRequestVo);
        return Resp.succ();
    }
}

