package spring.boot.sportplay.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yutimothy
 * @Date 2021/3/4 22:52
 * @Version 1.0
 */
public class JwtUtils {

    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "hsbmaaaaaaaaaa";

    /**
     * 生成签名,15分钟后过期
     *
     * @param roles
     * @param stuIdentity
     * @return
     */
    public static String sign(String roles, String stuIdentity) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create().withHeader(header).withClaim("roles", roles)
                .withClaim("stuIdentity", stuIdentity).withExpiresAt(date).sign(algorithm);
    }

    public static boolean verity(String token) {
        if (Strings.isNotBlank(token)) {
            return false;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
    }

    public static Map<String, Object> getInfoByToken(String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (Strings.isNotBlank(token)) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            //String stuIdentity = jwt.getClaim("stuIdentity").as(String.class);
            Map<String, Claim> claims = jwt.getClaims();
            Date expireTime = jwt.getExpiresAt();
            map.put("expireTime", expireTime);
            map.put("claims", claims);
            return map;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return null;
        }
    }

    public static String getStuIdentityByToken(String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (Strings.isNotBlank(token)) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String stuIdentity = jwt.getClaim("stuIdentity").as(String.class);
            return stuIdentity;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return null;
        }
    }

}

