package com.fashion.uaa.config.filter;

import com.fashion.uaa.config.tokens.MemberAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    public final static String LOGIN_URL = "/oauth/token";

    public AuthenticationTokenFilter() {
        super(new AntPathRequestMatcher(LOGIN_URL));
    }

    public AuthenticationTokenFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String authType = request.getParameter("authType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AbstractAuthenticationToken authRequest = null;
        if(authType != null && "member".equals(authType)){
            authRequest =  new MemberAuthenticationToken(username);
            authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
            return this.getAuthenticationManager().authenticate(authRequest);
        }else{
            authRequest = new UsernamePasswordAuthenticationToken(username,password);
        }
        return authRequest;
    }
}
