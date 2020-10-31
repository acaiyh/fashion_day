package com.fashion.uaa.config.handler;

import com.fashion.uaa.common.utils.JacksonUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 *  自定义AccessDeniedHandler来处理Ajax请求
 */
@Configuration
public class ClientAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        String responseJson = JacksonUtils.beanToJson(new HashMap<String, String>(){
            {
                put("code","403");
                put("message","Access is denied");
            }
        });
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(responseJson);
        response.getWriter().flush();
    }
}
