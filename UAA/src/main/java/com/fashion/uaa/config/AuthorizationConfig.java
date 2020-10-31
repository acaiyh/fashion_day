package com.fashion.uaa.config;

import com.fashion.uaa.service.impl.UAAClientDetailsService;
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
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    //存储token
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UAAClientDetailsService clientDetailsService;

    //由于改变了token加密，所以不需要存储token的服务了，在令牌安全约束配置里面也就不需要了
    /*@Autowired
    private AuthorizationCodeServices authorizationCodeServices;*/

    //认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    //配置客户端详情服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //用jdbc从数据库中拿
        clients.withClientDetails(clientDetailsService);
        //配置到内存
        /*clients.inMemory()
                .withClient("client01")
                .secret(new BCryptPasswordEncoder().encode("client01"))
                .resourceIds("res01")
                .authorizedGrantTypes("authorization_code", "password","client_credentials","implicit","refresh_token")//授权模式
                .scopes("all")
                .autoApprove(false) //自动跳转到用户授权页面 true:不跳转
                .redirectUris("http://www.baidu.com"); //回调地址*/
        //super.configure(clients);
    }

    //配置令牌端点的安全约束
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager) //你选择了资源所有者密码（password）授权类型的时候，请设置 这个属性注入一个 AuthenticationManager 认证管理器
                //.authorizationCodeServices(authorizationCodeServices) //授权码服务 //由于改变了token加密，所以不需要存储token的服务了
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenServices(tokenServices()) // 令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
                //设置授权码存储,没必要设置，若需要，实例化一个authorizationCodeServices Bean，注入进来
                //如果不设置，默认存入内存
                //.authorizationCodeServices(authorizationCodeServices);
                //.setClientDetailsService(clientDetailsService); //此处设置clientDetailService 和 tokenService()方法中上设置一样
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
    //后改变为JWT token
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setReuseRefreshToken(true);//刷新token
        defaultTokenServices.setTokenStore(tokenStore);//令牌存储策略
        defaultTokenServices.setAccessTokenValiditySeconds(7200); //令牌默认有效期2小时
        defaultTokenServices.setReuseRefreshToken(false); //设置为true,则没有refresh_token返回体中
        defaultTokenServices.setSupportRefreshToken(true);//设置支持refresh_token
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);//刷新令牌默认有效期3天
        //设置token的加密方式为JWT
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        return defaultTokenServices;
    }

    //设置授权码存储到数据库中，目前没必要设置，注释掉
    /*@Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
        return new JdbcAuthorizationCodeServices(dataSource);
    }*/

    //设置授权码模式的授权码如何存取，采用内存的方式
    //设置了token的加密方式是JWT，不使用授权码了，所以不需要配置存储授权码到内存
    /*@Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }*/

}
