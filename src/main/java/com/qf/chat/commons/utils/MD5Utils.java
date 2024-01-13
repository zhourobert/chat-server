package com.qf.chat.commons.utils;

import cn.hutool.crypto.digest.MD5;

public class MD5Utils {
    public static String getMD5String(String text){
        return MD5.create().digestHex(text);
    }
}
