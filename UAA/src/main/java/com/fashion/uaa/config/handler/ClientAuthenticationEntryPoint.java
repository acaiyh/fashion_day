package com.fashion.uaa.config.handler;

import com.fashion.uaa.common.utils.JacksonUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 *  如果用户请求了一个受保护的资源，但是没有通过认证，那么抛出异常，就会调用 commence方法
 *  这里手动处理一下
 */
@Configuration
public class ClientAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String responseJson = JacksonUtils.beanToJson(new HashMap<String, String>(){
            {
                put("code","401");
                put("message","You should sign in first.");
            }
        });
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(responseJson);
        response.getWriter().flush();
    }
}
