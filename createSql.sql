#及时通讯项目
#登录、注册、找回密码、发送好友申请、搜索好友、查看我的申请、
#通过/拒绝申请、好友列表、聊天（文本、图片、语音聊天、视频通话）

create database my_chat;

#用户表
create table user(
                     id int primary key auto_increment comment '主键',
                     username varchar(20) unique not null comment '用户名',
                     password char(32) not null comment '密码',
                     nickname varchar(10) not null comment '昵称',
                     header text not null comment '头像',
                     email varchar(30) not null unique comment '邮箱',
                     spell varchar(50) not null comment '昵称的拼音',
                     create_time datetime not null comment '创建时间',
                     update_time datetime not null comment '最后修改时间',
                     status tinyint not null comment '状态',
                     del_flag tinyint not null comment '删除标识'
) comment '用户表';

#好友申请表
create table friend_request(
                               id int primary key auto_increment comment '好友申请id',
                               u_id int not null comment '申请人id',
                               f_id int not null comment '被申请人id',
                               notes varchar(10) comment '申请人对被申请人的备注',
                               request_msg varchar(100) comment '验证消息',
                               create_time datetime not null comment '创建时间',
                               update_time datetime not null comment '最后修改时间',
                               status tinyint not null comment '状态 0-待处理 1-已通过 2-已拒绝',
                               del_flag tinyint not null comment '删除标识'
) comment '好友申请表';


#好友关系表
create table friend_relation(
                                id int primary key auto_increment comment '关系id',
                                u_id int not null comment '用户id',
                                f_id int not null comment '好友id',
                                notes varchar(10) comment '当前用户对好友的备注信息',
                                create_time datetime not null comment '创建时间',
                                update_time datetime not null comment '最后修改时间',
                                status tinyint not null comment '状态 0-普通好友 1-拉黑',
                                del_flag tinyint not null comment '删除标识',
#用户id 好友id 唯一性联合索引
                                unique index(u_id, f_id)
) comment '好友关系';


#历史消息表
create table msg_history(
                            id int primary key auto_increment comment '消息id',
                            u_id int not null comment '发送者id',
                            f_id int not null comment '接受者id',
                            msg text not null comment '消息的内容',
                            type tinyint not null comment '消息的类型 0-文本消息 1-图片消息 2-语音消息 3-视频通话',
                            create_time datetime not null comment '创建时间',
                            update_time datetime not null comment '最后修改时间',
                            status tinyint not null comment '状态 0-未接收 1-已接收',
                            del_flag tinyint not null comment '删除标识'
) comment '历史消息表';