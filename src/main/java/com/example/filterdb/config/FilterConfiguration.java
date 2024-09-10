package com.example.filterdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.filterdb.filter.LogFilter;
import com.example.filterdb.filter.RequestAndResponseLoggingFilter;
import com.example.filterdb.service.LogEntryService;

import lombok.extern.slf4j.Slf4j;


// @Configuration
// @Slf4j
public class FilterConfiguration {
   /*
    cf. https://www.baeldung.com/spring-autowire-bean-servlet-filter
	위의 예에서, 우리의 필터는 기본적으로 우리 애플리케이션의 모든 URL에 대해 등록됩니다. 그러나 때때로 우리는 특정 URL 패턴에만 적용되는 필터를 원할 수 있습니다.

	이 경우에는 필터 클래스 정의에서 @Component 주석을 제거하고 FilterRegistrationBean 을 사용하여 필터를 등록해야 합니다 .
	
	필터에 대한 URL 패턴을 설정하려면 addUrlPatterns() 또는 setUrlPatterns() 메서드를 사용할 수 있습니다.
	*/

	@Bean
    FilterRegistrationBean<LogFilter> loggingFilterRegistration(LogEntryService logEntryService) {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter(logEntryService));
        registrationBean.addUrlPatterns("/non-filter/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
    
    @Bean
    FilterRegistrationBean<RequestAndResponseLoggingFilter> loggingRequestAndResponseLoggingFilterRegistration(LogEntryService logEntryService) {
        FilterRegistrationBean<RequestAndResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestAndResponseLoggingFilter(logEntryService));
        registrationBean.addUrlPatterns("/filter/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    ///FilterRegistrationBean<LogFilter> logFilter() {
    ///	log.info("■ FilterConfiguration:logFilter 1");
    ///    FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
    ///
    ///    LogFilter logFilter = new LogFilter();
    ///    registrationBean.setFilter(logFilter);
    ///    // registrationBean.setUrlPatterns("/log/*"); // Collection<String>;
    ///    registrationBean.setName("Request Response Filter");
    ///    registrationBean.setAsyncSupported(Boolean.TRUE);
    ///    
    ///    // registrationBean.setOrder(1);
    ///    registrationBean.addUrlPatterns("/log/*");  // Apply filter to specific URLs
    ///	log.info("■ FilterConfiguration:logFilter 2");
    ///
    ///    return registrationBean;
    ///}
    
}
