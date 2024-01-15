package com.qf.chat.commons.returnresult;

import lombok.Getter;

@Getter
public enum RespCode {

    SUCC(200,"方法执行成功"),
    ERROR(500, "失败"),
    NO_POWER(406, "权限不足"),
    USER_NO_EXIST(407, "不存在该用户"),
    REPEAT_USER(409,"用户名重复");


    private final Integer code;
    private final String msg;

    RespCode(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
