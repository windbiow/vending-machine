package com.fuhe.chen.vendingmachine.intercepter;

import com.fuhe.chen.vendingmachine.common.HtmlCommons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseIntercepter implements HandlerInterceptor {

    @Autowired
    HtmlCommons htmlCommons;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("htmlCommons", htmlCommons);//一些工具类和公共方法
    }

}
