package com.robertZhou.chat.service.impl;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robertZhou.chat.commons.exception.ServiceException;
import com.robertZhou.chat.commons.returnresult.RespCode;
import com.robertZhou.chat.commons.utils.MD5Utils;
import com.robertZhou.chat.dao.UserDao;
import com.robertZhou.chat.entity.User;
import com.robertZhou.chat.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public List<User> queryUserListByNameNicknameAndSpell(String searchText) {
        if (searchText.isEmpty()) return null;
        return query().like("username",searchText)
                .or()
                .like("nickname",searchText)
                .or()
                .like("spell",searchText)
                .list();
    }
}

