package com.fashion.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    //认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;

    //配置客户端详情服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置到内存
        clients.inMemory()
                .withClient("client01")
                .secret(new BCryptPasswordEncoder().encode("client01"))
                .resourceIds("res01")
                .authorizedGrantTypes("authorization_code", "password","client_credentials","implicit","refresh_token")//授权模式
                .scopes("all")
                .autoApprove(false) //自动跳转到用户授权页面 true:不跳转
                .redirectUris("http://www.baidu.com"); //回调地址
        //super.configure(clients);
    }

    //配置令牌端点的安全约束
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager) //你选择了资源所有者密码（password）授权类型的时候，请设置 这个属性注入一个 AuthenticationManager 认证管理器
                .authorizationCodeServices(authorizationCodeServices) //授权码服务
                .tokenServices(tokenServices()) // 令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
        //super.configure(endpoints);
    }

    //配置令牌的管理服务，令牌端点
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()") //token/token_key
                .checkTokenAccess("permitAll()") //check_token
                .allowFormAuthenticationForClients();//允许表单认证client
        //super.configure(security);
    }

    //定义token的安全约束
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setReuseRefreshToken(true);//刷新token
        defaultTokenServices.setTokenStore(tokenStore);//令牌存储策略
        defaultTokenServices.setAccessTokenValiditySeconds(7200); //令牌默认有效期2小时
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);//刷新令牌默认有效期3天
        return defaultTokenServices;
    }

    //设置授权码模式的授权码如何存取，采用内存的方式
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }

}
