package com.robertZhou.chat.entity.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdatePasswordVo {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotNull
    private Integer verificationCode;
}
