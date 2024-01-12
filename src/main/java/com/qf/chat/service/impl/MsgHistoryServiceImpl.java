package com.qf.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.chat.dao.MsgHistoryDao;
import com.qf.chat.entity.MsgHistory;
import com.qf.chat.service.MsgHistoryService;
import org.springframework.stereotype.Service;

/**
 * 历史消息表(MsgHistory)表服务实现类
 *
 * @author makejava
 * @since 2024-01-12 12:12:08
 */
@Service("msgHistoryService")
public class MsgHistoryServiceImpl extends ServiceImpl<MsgHistoryDao, MsgHistory> implements MsgHistoryService {

}

