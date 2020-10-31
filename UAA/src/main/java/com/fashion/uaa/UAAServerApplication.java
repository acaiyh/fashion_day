package com.fashion.uaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.fashion.uaa.dao"})
public class UAAServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UAAServerApplication.class, args);
    }

}

/**
 * 授权模式分为四种：
 *      1：授权码模式：
 *          说明：授权码模式是比较安全的模式
 *          a：请求授权码，http://localhost:9999/uaa/oauth/authorize?client_id=client01&response_type=code&scope=all&redirect_uri=http://www.baidu.com (GET)
 *              response: https://www.baidu.com/?code=vqXLWR (授权码是 vqXLWR)
 *          b: 请求 token, http://localhost:9999/uaa/oauth/token （POST）
 *              参数：client_id=client01,client_secret=client01,grant_type=authorization_code,code=vqXLWR
 *              response: {
 *                        "access_token": "498a55d1-7a36-4386-9ae8-4312e852c707",
 *                        "token_type": "bearer",
 *                        "refresh_token": "50030476-1934-4a72-a4f9-3b3f14f5a597",
 *                        "expires_in": 4632,
 *                        "scope": "all"
 *                       }
 *      2：简化模式
 *          说明：简化模式多用于单一应用，没有服务器端的第三方单页面应用，因为没有服务器端就无法接收授权码
 *          a: 请求token, http://localhost:9999/uaa/oauth/authorize?client_id=client01&response_type=token&scope=all&redirect_uri=http://www.baidu.com (GET)
 *              注意： response_type 是token
 *              response: https://www.baidu.com/#access_token=498a55d1-7a36-4386-9ae8-4312e852c707&token_type=bearer&expires_in=4381
 *                  注：连接中的 ‘#’，URI 中的 fragment 用来标识次级资源
 *      3： 密码模式
 *          说明：密码模式一般用于我们自己开发的，第一方原生APP或者是第一方面页面应用；
 *                  因为会暴露密码出来，所以最好client端是自己开发的或者我们比较信任的
 *          a: 请求token : http://localhost:9999/uaa/oauth/token(POST)
 *              参数：client_id=client01,client_secret=client01,grant_type=password,username=zhangsan,password=123
 *             response: {
 *                         "access_token": "498a55d1-7a36-4386-9ae8-4312e852c707",
 *                         "token_type": "bearer",
 *                         "refresh_token": "50030476-1934-4a72-a4f9-3b3f14f5a597",
 *                         "expires_in": 3735,
 *                         "scope": "all"
 *                        }
 *      4：  客户端模式
 *          说明：我们对client足够信任的情况下使用客户端模式
 *          a: 请求token: http://localhost:9999/uaa/oauth/token
 *              参数： client_id=client01,client_secret=client01,grant_type=client_credentials
 *              response: {
 *                          "access_token": "27765be1-ea84-4551-9178-8a182ea02cbf",
 *                          "token_type": "bearer",
 *                          "expires_in": 7199,
 *                          "scope": "all"
 *                         }
 */
