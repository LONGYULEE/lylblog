package com.liyulong.blog.main.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 */
public class JwtUtil {

    //默认过期时间，两个小时，单位毫秒
    private static final long EXPIRE_TIME = 60 * 2 * 60 * 1000;

    //私钥
    private static final String TOKEN_SECRET = "hanlu";

    /**
     * 生成一个token，设置过期时间
     * @param userId 用户id
     * @param currentTimeMillis 时间戳
     * @return token
     */
    public static String createToken(String userId,String currentTimeMillis){
        try {
            //设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //设置私钥加密算法，userId + TOKEN_SECRET
            Algorithm algorithm = Algorithm.HMAC256(userId + TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> map = new HashMap<>();
            map.put("Type","Jwt");
            map.put("alg","HS256");
            //返回token字段
            return JWT.create()
                    .withHeader(map)
                    .withClaim("userId",userId)
                    .withClaim("currentTimeMillis",currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证token是否正确，验证失败直接抛出异常
     * @param token token
     * @return 是否正确
     */
    public static boolean verify(String token){
        token = token.replaceAll("Bearer ","");
        //获取密钥，userId + TOKEN_SECRET
        String secret = getClaim(token,"userId") +  TOKEN_SECRET;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
        return true;
    }

    /**
     * 获取Token中的信息，不需要secret解密
     * @param token token
     * @param claim
     * @return
     */
    public static String getClaim(String token,String claim){
        token = token.replaceAll("Bearer ","");
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }

}
