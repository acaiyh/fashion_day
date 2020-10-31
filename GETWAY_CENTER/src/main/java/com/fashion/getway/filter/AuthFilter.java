package com.fashion.getway.filter;

import com.alibaba.fastjson.JSON;
import com.fashion.getway.common.utils.EncryptUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.*;

/**
 * 对token进行拦截 解密 转发
 */
public class AuthFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return true; //需要拦截
    }

    @Override
    public int filterOrder() {
        return 0; //拦截优先
    }

    @Override
    public String filterType() {
        return "pre"; //访问前拦截
    }


    @Override
    public Object run() throws ZuulException {
        //通过zuul的上下文获取当前请求
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取令牌
        //通过security上下文获取获取用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //如果没有带uaa生成的token,则authentication是空的
        if(!(authentication instanceof OAuth2Authentication)){
            return null;
        }
        //获取oauth2权限
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        //如果userAuthentication为空，则token解析失败了
        if(userAuthentication == null){
            return null;
        }
        Object principal = userAuthentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userAuthentication.getAuthorities();
        Set<String> userAuths = new HashSet<>();
        if(authorities != null){
            authorities.stream().forEach(a -> userAuths.add(((GrantedAuthority) a).getAuthority()));
        }
        //获取oauth2认证后的请求
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        //组装明文请求头
        //拿到参数,加入用户名和权限
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        Map<String, Object> jsonData = new HashMap<>(requestParameters);
        jsonData.put("principal", principal);
        jsonData.put("userAuths", userAuths);
        requestContext.addZuulRequestHeader("json-data", EncryptUtil.encodeUTF8StringBase64(JSON.toJSONString(jsonData)));
        return null;
    }
}
