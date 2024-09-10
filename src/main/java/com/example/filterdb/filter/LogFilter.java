package com.example.filterdb.filter;

//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.filterdb.service.LogEntryService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
// import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

// @WebFilter(urlPatterns = "/api/filter") //필터를 적용할 uri를 설정한다.
// @WebFilter("/log/*")
// @WebFilter(urlPatterns="/log/*")
//@WebFilter(urlPatterns = "/api/filter") //필터를 적용할 uri를 설정한다.
// @ChatGPT 답 테스트
@Slf4j
@Component
//@Order(1)
//@WebFilter(
//		urlPatterns = "/non-filter/*"
//) //필터를 적용할 uri를 설정한다.
public class LogFilter implements Filter {

	//@Override
	//public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	//		throws IOException, ServletException {
	//	// TODO Auto-generated method stub
	//	
	//}

	//default void init(FilterConfig filterConfig) throws ServletException {
    //}
	
    // @Autowired
    private LogEntryService logEntryService;

    public LogFilter(LogEntryService logEntryService) {
    	this.logEntryService = logEntryService;
    }
    // 
    @Override // FilterConfig filterConfig
	public void init(
			jakarta.servlet.FilterConfig filterConfig
			//com.example.filterdb.config.FilterConfig filterConfig
			) throws ServletException {
        // Initialization code if necessary
    	log.info("■ Initialization code if needed");
    	// Initialization code if needed
    	log.info("■ filterName:{}", filterConfig.getFilterName() );
    	//filterConfig.getServletContext().
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        log.info("■ doFilter I.");
    
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestData = httpRequest.getParameter("data");
    
        if (requestData != null) {
            logEntryService.saveLogEntry(requestData);  // Save to DB
        }
    
        // Continue with the request
        chain.doFilter(request, response);
        
        log.info("■ doFilter O.");
    }

    @Override
    public void destroy() {
        // Cleanup code if necessary
    	log.info("■ destroy ...");
    }
}
