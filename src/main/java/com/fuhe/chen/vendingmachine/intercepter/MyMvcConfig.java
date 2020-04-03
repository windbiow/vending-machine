package com.fuhe.chen.vendingmachine.intercepter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2020/3/8.
 */
@Component
public class MyMvcConfig implements WebMvcConfigurer {


    @Autowired
    private BaseIntercepter baseIntercepter;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseIntercepter);
    }


}
