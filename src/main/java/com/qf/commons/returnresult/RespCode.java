package com.qf.commons.returnresult;

import lombok.Getter;

@Getter
public enum RespCode {

    SUCC(200,"方法执行成功"),
    ERROR(500, "失败"),
    NO_POWER(406, "权限不足"),
    HOUSE_EXISTE(407, "房屋标题已经存在"),
    HOUSE_NO_HEGE(408, "房屋资质不合格");

    private final Integer code;
    private final String msg;

    RespCode(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
