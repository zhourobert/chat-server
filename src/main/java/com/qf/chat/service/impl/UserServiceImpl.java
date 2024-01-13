package com.qf.chat.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.chat.commons.exception.ServiceException;
import com.qf.chat.commons.returnresult.RespCode;
import com.qf.chat.commons.utils.MD5Utils;
import com.qf.chat.dao.UserDao;
import com.qf.chat.entity.User;
import com.qf.chat.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-01-12 12:12:08
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Override
    public boolean save(User entity) {
        //效验是否有同名对象
        User user = query().eq("username", entity.getUsername()).one();
        if (user!=null){
            throw new ServiceException(RespCode.REPEAT_USER);
        }
        //密码加密
        entity.setPassword(MD5Utils.getMD5String(entity.getPassword()));
        //拼音填入
        entity.setSpell(PinyinUtil.getPinyin(entity.getNickname(),""));
        return super.save(entity);
    }
}

