package com.robertZhou.chat.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ClassName:FriendListAndLastMsgVo
 * package:com.qf.chat.entity.vo
 * Description:
 *
 * @Author:周蕴韬
 * @Create 2024/2/17 16:57
 * Version 1.0
 */
@Data
@Accessors(chain = true)
public class FriendAndLastMsgVo {
    private Integer fId;
    private String nickname;
    private String notes;
    private String header;
    private String lastMsg;

}
