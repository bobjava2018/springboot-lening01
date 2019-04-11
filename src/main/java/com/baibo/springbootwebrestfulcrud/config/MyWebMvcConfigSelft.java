package com.baibo.springbootwebrestfulcrud.config;


import com.baibo.springbootwebrestfulcrud.component.LoginInterceptor;
import com.baibo.springbootwebrestfulcrud.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class MyWebMvcConfigSelft implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/index","/main").excludePathPatterns("/index.html","/static/**");

        System.out.println("拦截器-----------------------");
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("试图----------------------");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/Dashboard").setViewName("Dashboard");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("资源----------------------");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");

    }
    @Bean
    public LocaleResolver localeResolver(){

        System.out.println("国际化----------------------");
        return new MyLocaleResolver();
    }
}
