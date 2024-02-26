package com.robertZhou.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robertZhou.chat.entity.MsgHistory;
import com.robertZhou.chat.entity.vo.FriendAndLastMsgVo;

import java.util.List;

/**
 * 历史消息表(MsgHistory)表服务接口
 *
 * @author makejava
 * @since 2024-01-12 12:12:08
 */
public interface MsgHistoryService extends IService<MsgHistory> {

    List<FriendAndLastMsgVo> loadFriendsAndLastMsgs(Integer id);
}

