package com.jankin.springboot.demo.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lyy
 * 创建时间 2019/6/10 21:00
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public TestInterceptor getTrackInterceptor() {
        return new TestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTrackInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/**");
    }
}
