package com.qf.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.chat.entity.User;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-01-12 12:12:08
 */
public interface UserService extends IService<User> {


    List<User> queryUserListByNameNicknameAndSpell(String searchText);
}

