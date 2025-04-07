package com.example.boot_schedule_ver2.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  웹 설정 클래스
 *  - 필터 등록 및 설정을 담당합니다.
 *  @Configuration: Spring 설정 클래스임을 나타내는 어노테이션입니다.
 */
@Configuration
public class WebConfig {

    /**
     *  로그인 필터 등록 Bean
     *  - LoginFilter 를 FilterRegistrationBean 을 통해 등록하고, 필터 순서 및 URL 패턴을 설정합니다.
     *  @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter()); // LoginFilter 등록
        filterRegistrationBean.setOrder(1); // 필터 순서 설정 (낮을수록 먼저 실행)
        filterRegistrationBean.addUrlPatterns("/*"); // 필터가 적용될 URL 패턴 설정 ("/*" -> 전체 URL)

        return filterRegistrationBean;
    }
}
