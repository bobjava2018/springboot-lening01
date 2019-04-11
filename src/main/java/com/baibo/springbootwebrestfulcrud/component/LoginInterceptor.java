package com.baibo.springbootwebrestfulcrud.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       // logger.info("--------------"+request.getParameter("username")+","+request.getParameter("password"));
        System.out.println("=================进入拦截器了额");
        HttpSession session =request.getSession();
        String name = (String) session.getAttribute("loginName");

        if (StringUtils.isEmpty(name)) {
            logger.info("拦截器拦截");
            request.getRequestDispatcher("/crud/index").forward(request,response);
            return false;
        }
         logger.info("拦截器放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("=================处理请求");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("=================结束处理请求");
    }
}
