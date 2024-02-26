package com.robertZhou.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robertZhou.chat.dao.MsgHistoryDao;
import com.robertZhou.chat.entity.MsgHistory;
import com.robertZhou.chat.entity.vo.FriendAndLastMsgVo;
import com.robertZhou.chat.service.MsgHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 历史消息表(MsgHistory)表服务实现类
 *
 * @author makejava
 * @since 2024-01-12 12:12:08
 */
@Service("msgHistoryService")
public class MsgHistoryServiceImpl extends ServiceImpl<MsgHistoryDao, MsgHistory> implements MsgHistoryService {

    @Override
    public List<FriendAndLastMsgVo> loadFriendsAndLastMsgs(Integer id) {
        return getBaseMapper().loadFriendsAndLastMsgs(id);
    }
}

