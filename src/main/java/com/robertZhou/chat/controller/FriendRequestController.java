package com.robertZhou.chat.controller;



import com.robertZhou.chat.commons.base.BaseController;
import com.robertZhou.chat.commons.returnresult.Resp;
import com.robertZhou.chat.commons.returnresult.RespCode;
import com.robertZhou.chat.commons.utils.JWTUtils;
import com.robertZhou.chat.entity.FriendRequest;
import com.robertZhou.chat.entity.vo.HandleRequestVo;
import com.robertZhou.chat.service.FriendRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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

    @GetMapping("/getFriendToMeRequest")
    public Resp getFriendToMeRequest(){
        Integer uid=JWTUtils.getId();
        log.debug("后端进入getFriendToMeRequest获取令牌uid：{}",uid);
//        List<FriendRequest> friendRequests = getService().query().eq("f_id", uid).list();
        List<FriendRequest> friendRequests = getService().getFriendRequestList(uid);
        return Resp.succ(friendRequests);
    }
    @GetMapping("/getMeToFriendRequest")
    public Resp getMeToFriendRequest(){
        Integer uid=JWTUtils.getId();
        log.debug("后端进入getMeToFriendRequest获取令牌uid：{}",uid);
//        List<FriendRequest> friendRequests = getService().query().eq("f_id", uid).list();
        List<FriendRequest> friendRequests = getService().getMyRequestList(uid);
        return Resp.succ(friendRequests);
    }

    @PatchMapping ("/patchRequest")
    public Resp patchRequest(@RequestBody HandleRequestVo handleRequestVo){
        log.debug("后端进入patchRequest获取要修改的HandleRequestVo：{}",handleRequestVo);
        getService().handleRequest(handleRequestVo);
        return Resp.succ();
    }
}

