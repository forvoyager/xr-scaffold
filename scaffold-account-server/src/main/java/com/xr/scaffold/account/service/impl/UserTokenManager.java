package com.xr.scaffold.account.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.util.DateUtil;
import com.xr.base.core.util.StringUtils;
import com.xr.scaffold.account.common.model.UserModel;
import com.xr.scaffold.account.common.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2021-08-08 16:10:00
 * <b>@description</b>: 用户token管理器
 */
@Component("userTokenManager")
public class UserTokenManager {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expire-time-in-minutes:300}")
  private int expireTimeInMinutes;

  @Value("${jwt.issuer}")
  private String issuer;

  @Value("${jwt.subject}")
  private String subject;

  @Value("${jwt.audience}")
  private String audience;

  @Autowired
  private IUserService userService;

  private TokenExpiredException expiredException =  new TokenExpiredException("生成token失败");

  /**
   * 生成用户的token
   * @param userId
   * @return
   * @throws Exception
   */
  public String generateToken(Long userId) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("alg", "HS256");
    map.put("typ", "JWT");

    Algorithm algorithm = Algorithm.HMAC256(this.secret);

    Date nowDate = new Date();
    // 过期时间：2小时
    Date expireDate = DateUtil.addMinute(this.expireTimeInMinutes);
    String token = JWT.create()
            // 设置头部信息 Header
            .withHeader(map)
            // 设置 载荷 Payload
            .withClaim("userId", userId)
            .withIssuer(this.issuer)
            .withSubject(this.subject)
            .withAudience(this.audience)
            // 生成签名的时间
            .withIssuedAt(nowDate)
            // 签名过期的时间
            .withExpiresAt(expireDate)
            // 签名 Signature
            .sign(algorithm);

    if(token==null || token.isEmpty()){ throw new IllegalStateException("生成token失败"); }

    return token;
  }

  /**
   * 根据token获取用户id
   * @param token
   * @return
   */
  public Long getUserId(String token) throws Exception {

    // 没有带token，返回401跳登录页
    if(StringUtils.isEmpty(token)){ throw expiredException; }

    Algorithm algorithm = Algorithm.HMAC256(this.secret);
    JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(this.issuer)
            .build();
    DecodedJWT jwt = verifier.verify(token);
    Map<String, Claim> claims = jwt.getClaims();
    Claim claim = claims.get("userId");
    Long userId = claim.asLong();

    // token不合法
    if(userId == null || userId == 0){ throw expiredException; }

    // 用户不存在，都返回token过期
    UserModel user = userService.selectById(userId, Cluster.master);
    if(user == null){ throw expiredException; }

    return userId;
  }
}
