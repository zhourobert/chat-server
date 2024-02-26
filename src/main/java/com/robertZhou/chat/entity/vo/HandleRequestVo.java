package com.robertZhou.chat.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HandleRequestVo {

    Integer friendRequestId;

    Integer statusCode;
}
