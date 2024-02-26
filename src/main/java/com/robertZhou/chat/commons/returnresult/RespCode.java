package com.robertZhou.chat.commons.returnresult;

import lombok.Getter;

@Getter
public enum RespCode {

    SUCC(200,"方法执行成功"),
    ERROR(500, "失败"),
    NO_POWER(406, "权限不足"),
    USER_NO_EXIST(407, "不存在该用户"),
    REPEAT_USER(409,"用户名重复"),
    MAIL_ERROR(410,"邮件发送失败"),
    WRONG_MAIL_ADDRESS(411,"邮箱地址错误"),
    WRONG_CODE(412,"验证码错误"),
    DUPLICATE_PWD(413,"密码重复错误"),
    LOGIN_ERROR(414,"账号或密码错误"),
    WRONG_TOKEN(415,"令牌失效或不正确");


    private final Integer code;
    private final String msg;

    RespCode(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
