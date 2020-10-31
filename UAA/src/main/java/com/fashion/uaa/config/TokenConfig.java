package com.fashion.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {

    /*//token 令牌存入内存
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }*/
    private String SIGNING_KEY = "fashion_000";

    /**
     * JWT 令牌介绍
     *      JWT令牌中包含了用户的相关信息，客户端携带令牌访问资源，资源服务器通过约定算法完成令牌的校验，
     *      无需在远程通过授权服务校验令牌了；
     *      什么是JWT
     *          1: JWT(JSON WEB TOKEN)是一个开放的行业标准（FRC 7519），它定义了一种简介的，自包含的协议
     *          格式，用于通信双方传递json对象，传递信息经过数字签名可以被验证和信任。JWT可以使用HMAC算法或者
     *          RSA公钥/私钥来对签名防止篡改。
     *          JWT特点：
     *              a, JWT基于json，方便解析
     *              b, 可以自定义内容，易扩展
     *              c, 通过非对称或者对称算法及数字签名，防止篡改，安全性高
     *              d, 资源服务器使用JWT不用依赖认证服务器校验token即可完成授权
     *         JWT缺点：
     *              a, JWT令牌长，占用空间大
     *     JWT令牌结构：
     *          - Header : 头部包括令牌的类型以及算法
     *          - Payload ：负载，内容是一个json对象，存放信息的地方，比如：iss(签发者），exp(过期时间), sub(面向的用户) 等，也可自定义字段
     *          - Signature ：签名，防止篡改
     *
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(SIGNING_KEY);
        return tokenConverter;
    }

}
