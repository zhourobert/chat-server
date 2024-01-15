package com.qf.chat.commons.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    //密钥
    private static String secretKey = "f4a29eb6-4bb5-4c3a-bc9a-825ee55a2f02";
    //令牌有效期
    private static long ttl = 1000 * 60 * 60 * 2;
//    private static long ttl = 1000 * 10;

    //补签的有效时间
    private static long flag = 1000 * 60 * 60 * 4;

    /**
     * 生成jwt令牌
     */
    public static Builder createJwtToken(){
        return new Builder();
    }

    /**
     * 创建构建器对象
     */
    public static class Builder{
        private Map<String, Object> map = new HashMap<>();

        public Builder add(String key, Object value) {
            map.put(key, value);
            return this;
        }

        public String build(){
            long now = System.currentTimeMillis();
            JwtBuilder jwtBuilder = Jwts.builder()
                    //自定义令牌中的数据
                    .setClaims(map)
                    //设置签发时间
                    .setIssuedAt(new Date(now))
                    //设置加密算法以及密钥
                    .signWith(SignatureAlgorithm.HS256,secretKey);
            if(ttl > 0){
                //设置令牌的过期时间
                jwtBuilder.setExpiration(new Date(now + ttl));
            }
            return jwtBuilder.compact();
        }
    }

    /**
     * 解析jwt令牌
     * @param jwtToken
     * @return
     */
    public static <T> T parseJwtToken(String jwtToken, String key){
        try {
            return (T) Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken).getBody().get(key);
        } catch (ExpiredJwtException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
