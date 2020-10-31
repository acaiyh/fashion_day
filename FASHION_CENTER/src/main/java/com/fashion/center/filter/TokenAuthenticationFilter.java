package com.fashion.center.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fashion.center.common.utils.EncryptUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("json-data");
        if(token != null){
            //token不为空，解析token,网关跳转过来的
            String json = EncryptUtil.decodeUTF8StringBase64(token);
            JSONObject userJson = JSON.parseObject(json);
            String principal = (String) userJson.get("principal");
            JSONArray userAuths = userJson.getJSONArray("userAuths");
            String[] authorities = new String[userAuths.size()];
            if(userAuths != null){
                authorities = userAuths.toArray(authorities);
            }
            //构建一个认证权限 authentication
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    principal
                    ,null
                    , AuthorityUtils.createAuthorityList(authorities)
                    );
            //加入请求体
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //将构建好的authentication加入security的上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
