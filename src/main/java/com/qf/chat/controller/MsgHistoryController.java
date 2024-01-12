package com.qf.chat.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.chat.commons.base.BaseController;
import com.qf.chat.entity.MsgHistory;
import com.qf.chat.service.MsgHistoryService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 历史消息表(MsgHistory)表控制层
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
@RestController
@RequestMapping("msgHistory")
public class MsgHistoryController extends BaseController<MsgHistoryService> {
    
}

