package com.robertZhou.chat.entity;


import com.robertZhou.chat.commons.base.BaseEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2024-01-12 12:12:08
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class User extends BaseEntity {

//用户名
    @NotEmpty
    private String username;
//密码
    @NotEmpty
    private String password;
//昵称
    @NotEmpty
    private String nickname;
//头像
    @NotEmpty
    private String header;
//邮箱
    @Email
    private String email;
//昵称的拼音
    private String spell;





}

