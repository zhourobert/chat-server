package com.robertZhou.chat.commons.utils;

import com.robertZhou.chat.commons.exception.ServiceException;
import com.robertZhou.chat.commons.returnresult.RespCode;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JWTUtils {

    //密钥
    private static String secretKey = "f4a29eb6-4bb5-4c3a-bc9a-825ee55a2f02";
    //令牌有效期 2天
    private static long ttl = 1000 * 60 * 60 * 2;
//    private static long ttl = 1000 * 10;

    //补签的有效时间 4天
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
    public static String getTokenFromHeader(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request.getHeader("Token");

    }

    public static boolean verify(Integer id){
        String token=getTokenFromHeader();
        Integer id1 = parseJwtToken(token, "id");
        System.out.println("后台获取jwt令牌为"+id1);
        if (Objects.equals(id, id1)) return true;
        return false;
    }

    public static Integer getId(){
        String token=getTokenFromHeader();
        //解析令牌
        Integer uid = parseJwtToken(token, "id");
        if (uid == null) {
            throw new ServiceException(RespCode.WRONG_TOKEN);
        }
        return uid;
    }

}
