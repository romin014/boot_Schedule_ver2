package com.example.boot_schedule_ver2.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter()); //LoginFilter 등록
        filterRegistrationBean.setOrder(1); //필터 순서 설정
        filterRegistrationBean.addUrlPatterns("/*");//필터가 적용될 url 설정 ("/*")-> 전체 url

        return filterRegistrationBean;
    }
}

