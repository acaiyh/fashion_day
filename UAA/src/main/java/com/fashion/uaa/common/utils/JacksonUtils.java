package com.fashion.uaa.common.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JacksonUtils {

    private static ObjectMapper objectMapper;

    private JacksonUtils(){}

    public static ObjectMapper getObjectMapper(){
        if(objectMapper == null){
            synchronized (Object.class){
                if(objectMapper == null){
                    objectMapper = new ObjectMapper();
                }
            }
        }
        return objectMapper;
    }

    public static String beanToJson(Object bean) throws IOException {
        ObjectMapper objectMapper = JacksonUtils.getObjectMapper();
        String json = objectMapper.writeValueAsString(bean);
        return json;
    }

}
