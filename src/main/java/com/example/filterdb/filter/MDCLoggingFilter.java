package com.example.filterdb.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // @Order 애노테이션을 사용하여 빈의 우선 순위를 설정할 때, 같은 우선 순위를 가진 빈들 간의 상대적인 순서는 보장되지 않는다.
public class MDCLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	String uuid = UUID.randomUUID().toString();
    	MDC.put("GID", uuid);
    
        // Continue with the request
        chain.doFilter(request, response);
        
        MDC.clear();
    }
}

// override
// fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
//val uuid = UUID.randomUUID();        MDC.put("request_id", uuid.toString());        chain.doFilter(request, response);        MDC.clear();    } }
//출처: https://0soo.tistory.com/246 [Lifealong:티스토리]
