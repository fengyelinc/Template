package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * todo:springMVC配置文件
 * 配置拦截器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
//        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyHandlerInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login",
//                        "/login/main",
//                        "/logout",
//                        "/genCaptcha",
//                        "/static/**",
//                        "/loginByToken",
//                        "/admin/system/dept/dtreedata",
//                        "/admin/system/dict/selectDictByType",
//                        "/admin/system/dept/tree",
//                        "/app/**",
//                        "/cab/**"/*,
//                        "/zbgl/dp/**"*/
//                );
//    }

    /**
     * 配置外部HandlerMethodArgumentResolver
     */
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new LayuiPageMethodArgumentResolver());
//    }
}
