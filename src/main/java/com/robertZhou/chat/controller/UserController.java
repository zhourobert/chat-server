package com.robertZhou.chat.controller;



import com.robertZhou.chat.commons.base.BaseController;
import com.robertZhou.chat.commons.returnresult.Resp;
import com.robertZhou.chat.commons.returnresult.RespCode;
import com.robertZhou.chat.commons.utils.JWTUtils;
import com.robertZhou.chat.commons.utils.MD5Utils;
import com.robertZhou.chat.commons.utils.MailUtils;
import com.robertZhou.chat.entity.User;
import com.robertZhou.chat.entity.vo.LoginVo;
import com.robertZhou.chat.entity.vo.UpdatePasswordVo;
import com.robertZhou.chat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    @Autowired
    MailUtils mailUtils;
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
    @GetMapping("/login")
    public Resp login(LoginVo loginVo){
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
            return Resp.fail(RespCode.LOGIN_ERROR);
        }
    }

    @GetMapping("/getHeader")
    public Resp getHeader(String username){
        log.debug("后端获取username：{}",username);
        User user=getService().query()
                .eq("username",username)
                .one();
        if (user!=null){
            return Resp.succ(user.getHeader());
        }
        return Resp.fail(RespCode.USER_NO_EXIST);
    }

    @GetMapping("/sendEmail")
    public Resp sendEmail(String username){
        log.debug("后端获取username：{}",username);
        User user = getService().query()
                .eq("username", username)
                .one();
        if (user==null){

            return Resp.fail(RespCode.USER_NO_EXIST);
        }
        //验证码
        int codes = (int)(Math.random() * 9000 + 1000);
        //获取邮箱
        String email = user.getEmail();

        mailUtils.sendMail(email,username,codes);
        //返回隐藏后的邮箱地址
        String content = email.substring(3, email.lastIndexOf("@"));
        email = email.replace(content, "******");

        return Resp.succ("邮件成功发送到"+email+"邮箱！");
    }
    @PostMapping("/updatePassword")
    public Resp updatePassword(@RequestBody @Validated UpdatePasswordVo updatePasswordVo){
        log.debug("后端获取updatePasswordVo：{}",updatePasswordVo);
        Integer code = MailUtils.codesMap.get(updatePasswordVo.getUsername());
        if (!Objects.equals(code,updatePasswordVo.getVerificationCode())){
            return Resp.fail(RespCode.WRONG_CODE);
        }
        User user = getService().query().eq("username", updatePasswordVo.getUsername()).one();
        String encodePwd=MD5Utils.getMD5String(updatePasswordVo.getPassword());
        if (Objects.equals(encodePwd,user.getPassword())){
            return Resp.fail(RespCode.DUPLICATE_PWD);
        }
        getService().update().set("password",encodePwd)
                .eq("username",updatePasswordVo.getUsername())
                .update();
        MailUtils.codesMap.remove(updatePasswordVo.getUsername());
        return Resp.succ();
    }
    @GetMapping("/searchFriends")
    public Resp searchFriends(String searchText){
        log.debug("后端获取searchText：{}",searchText);
        List<User> users=getService().queryUserListByNameNicknameAndSpell(searchText);
        return Resp.succ(users);
    }


    @GetMapping("/verifyToken")
    public Resp verifyToken(Integer id){
        log.debug("后端获取id：{}",id);
        if(JWTUtils.verify(id)) return Resp.succ();
        return Resp.fail(RespCode.WRONG_TOKEN);
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

