package com.robertZhou.chat.commons.returnresult;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Resp<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;


    public static <T> Resp<T> succ(T data){
        return new Resp<T>()
                .setCode(RespCode.SUCC.getCode())
                .setMsg(RespCode.SUCC.getMsg())
                .setData(data);
    }

    public static <T> Resp<T> succ(){
        return succ(null);
    }

    public static <T> Resp<T> fail(RespCode respCode,T data){
        return new Resp<T>()
                .setCode(respCode.getCode())
                .setMsg(respCode.getMsg())
                .setData(data);
    }

    public static <T> Resp<T> fail(RespCode respCode){
        return new Resp<T>()
                .setCode(respCode.getCode())
                .setMsg(respCode.getMsg());
    }

}
