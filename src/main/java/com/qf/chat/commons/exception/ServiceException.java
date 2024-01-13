package com.qf.chat.commons.exception;

import com.qf.chat.commons.returnresult.RespCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceException extends RuntimeException{
    private RespCode respCode;
}
