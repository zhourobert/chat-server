package com.qf.chat.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.chat.commons.base.BaseController;
import com.qf.chat.entity.User;
import com.qf.chat.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表(User)表控制层
 *
 * @author makejava
 * @since 2024-01-12 12:12:08
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserService> {
    
}

