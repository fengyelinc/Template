package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.demo.entity.User;

import java.util.Date;
import java.util.UUID;

public class JWTHelper {
    private static final String ISSUER = "FENG_YE_LIN";
    /*------------------------------Using RS256---------------------------------*/
    /*获取签发的token，返回给前端*/
    public static String generTokenByRS256(User user) throws Exception{
        RSA256Key rsa256Key = SecretKeyHelper.getRSA256Key(); // 获取公钥/私钥
        Algorithm algorithm = Algorithm.RSA256(
                rsa256Key.getPublicKey(),rsa256Key.getPrivateKey());
        return createToken(algorithm, user);
    }

    /*签发token*/
    public static String createToken(Algorithm algorithm, Object data) throws Exception {
        String[] audience  = {"app"};
        return JWT.create()
                .withIssuer(ISSUER)   		//发布者
                .withAudience(audience)     //观众，相当于接受者
                .withIssuedAt(new Date())   // 生成签名的时间
                .withExpiresAt(DateHelper.addMinute(new Date(),30))// 生成签名的有效期
                .withClaim("data", JSON.toJSONString(data)) //存数据
                .withNotBefore(new Date())  //生效时间
                .withJWTId(UUID.randomUUID().toString())    //编号
                .sign(algorithm);							//签入
    }

    /*验证token*/
    public static DecodedJWT verifierToken(String token)throws Exception{
        RSA256Key rsa256Key = SecretKeyHelper.getRSA256Key(); // 获取公钥/私钥
        //其实按照规定只需要传递 publicKey来校验即可,这可能是auth0 的缺点
        Algorithm algorithm = Algorithm.RSA256(rsa256Key.getPublicKey(), rsa256Key.getPrivateKey());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build(); //Reusable verifier instance 可复用的验证实例
        return verifier.verify(token);
    }


    public static User getUserInfo(String token) throws Exception {
        DecodedJWT jwt = verifierToken(token);
        return JSON.parseObject(jwt.getClaim("data").asString(),User.class);
    }

}