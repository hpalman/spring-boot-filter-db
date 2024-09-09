package com.example.filterdb.config;

import com.example.filterdb.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<LogFilter> logFilter() {
//        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LogFilter());
//        registrationBean.addUrlPatterns("/log/*");  // Apply filter to specific URLs
//
//        return registrationBean;
//    }
//}
