package com.robertZhou.chat.controller;



import com.robertZhou.chat.commons.base.BaseController;
import com.robertZhou.chat.commons.returnresult.Resp;
import com.robertZhou.chat.commons.returnresult.RespCode;
import com.robertZhou.chat.commons.utils.JWTUtils;
import com.robertZhou.chat.entity.vo.FriendAndLastMsgVo;
import com.robertZhou.chat.service.MsgHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 历史消息表(MsgHistory)表控制层
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@RestController
@RequestMapping("/msgHistory")
@Slf4j
public class MsgHistoryController extends BaseController<MsgHistoryService> {
    @GetMapping("/getFriendList")
    public Resp getFriendList(Integer id){
        log.debug("后端获取friendRequest：{}",id);
        //验证id是否正确
        if (!JWTUtils.verify(id)) {
            return Resp.fail(RespCode.WRONG_TOKEN);
        }
        //id正确需要查询返回该用户的所有好友备注，头像及最后聊天的一句话
        List<FriendAndLastMsgVo> friendAndLastMsgVoList = getService().loadFriendsAndLastMsgs(id);

        friendAndLastMsgVoList.forEach((e)->{
            e.setLastMsg("");
        });
        return Resp.succ(friendAndLastMsgVoList);

    }
}

