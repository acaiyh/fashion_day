package com.fashion.center.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    //由于用了JWT加密token,所以资源服务需要自己解析token
    @Autowired
    TokenStore tokenStore;

    private static final String RESOURCE_ID = "res01";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                //由于用了JWT，所以不用token service来存储token了
                //.tokenServices(tokenServices())
                .tokenStore(tokenStore)
                .stateless(true); ////设置security不会创建session,也不会获取session上下文
        //super.configure(resources);
    }


    //此处用RemoteTokenServices 远程调用认证服务进行token校验
    //用了 JWT之后就不再需要远程调用认证服务校验token了，注释不用了
    /*@Bean
    public ResourceServerTokenServices tokenServices(){
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl("http://localhost:9999/uaa/oauth/check_token");
        tokenServices.setClientId("client01");
        tokenServices.setClientSecret("client01");
        return tokenServices;
    }*/
}
