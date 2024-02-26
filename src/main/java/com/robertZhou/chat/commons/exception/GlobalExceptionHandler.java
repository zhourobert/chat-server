package com.robertZhou.chat.commons.exception;

import com.robertZhou.chat.commons.returnresult.Resp;
import com.robertZhou.chat.commons.returnresult.RespCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 返回未预料的错误
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Resp globalExceptionHandler(Exception e){
        log.error("未预料的全局异常错误",e);
        return Resp.fail(RespCode.ERROR);
    }
}
