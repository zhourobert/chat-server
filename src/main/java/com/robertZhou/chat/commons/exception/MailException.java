package com.robertZhou.chat.commons.exception;

import com.robertZhou.chat.commons.returnresult.RespCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailException extends RuntimeException{
    private RespCode respCode;
}
