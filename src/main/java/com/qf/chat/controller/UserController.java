package com.qf.chat.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.chat.commons.base.BaseController;
import com.qf.chat.commons.returnresult.Resp;
import com.qf.chat.commons.returnresult.RespCode;
import com.qf.chat.commons.utils.JWTUtils;
import com.qf.chat.commons.utils.MD5Utils;
import com.qf.chat.entity.User;
import com.qf.chat.entity.vo.LoginVo;
import com.qf.chat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController<UserService> {
    /**
     * 登陆接口
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Resp register(@RequestBody @Validated User user){
        log.debug("后端获取User：{}",user);
        //service层处理业务
        boolean save = getService().save(user);
        if (save){
            return Resp.succ();
        }
        return Resp.fail(RespCode.ERROR);
    }

    /**
     * 登陆并且返回jwt令牌
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    public Resp login(@RequestBody LoginVo loginVo){
        log.debug("后端获取loginVo：{}",loginVo);
        User user = getService().query()
                .eq("username", loginVo.getUsername())
                .eq("password", MD5Utils.getMD5String(loginVo.getPassword()))
                .one();
        if (user!=null){
            //将用户信息 - user对象 -> 转换成Jwt令牌，返回给客户端
            String jwtToken = JWTUtils.createJwtToken()
                    .add("id", user.getId())
                    .add("nickname", user.getNickname())
                    .add("header", user.getHeader())
                    .build();


            return Resp.succ(jwtToken);
        }else {
            return Resp.fail(RespCode.USER_EXIST);
        }
    }

    /**
     * 登陆并且返回jwt令牌
     * @param
     * @return
     */
    @PostMapping("/test")
    public Resp test(@RequestParam("username") String username,@RequestParam("password") String password){
        log.debug("后端获取test：{}",username);
        log.debug("后端获取test：{}",password);
        return Resp.succ();
    }
}

