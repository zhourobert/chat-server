package com.qf.chat.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginVo {
    private String username;
    private String password;
}
