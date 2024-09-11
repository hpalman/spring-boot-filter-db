package com.example.filterdb.filter;

//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.filterdb.multidb.model.UserMySQL;
import com.example.filterdb.multidb.model.UserPostgres;
import com.example.filterdb.multidb.service.UserMySQLService;
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
//@Component
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
    //// private LogEntryService logEntryService;
    //// public LogFilter(LogEntryService logEntryService) {
    //// 	this.logEntryService = logEntryService;
    //// }

    private UserMySQLService userMySQLService; 
    public LogFilter(UserMySQLService userMySQLService) {
    	this.userMySQLService = userMySQLService;
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

        String gid = MDC.get("GID");
        String uri = httpRequest.getRequestURI();
        //Long   id    = Long.parseLong( httpRequest.getParameter("id") );
        //String name  = httpRequest.getParameter("name");
        //String email = httpRequest.getParameter("email");
        //UserMySQL user = new UserMySQL();
        //user.setId(id);
        //user.setName(name);
        //user.setId(id);

        //if (requestData != null) {
            //// logEntryService.saveLogEntry(requestData);  // Save to DB
            userMySQLService.log(gid, uri);
        //}
    
        // Continue with the request
        try {
        	chain.doFilter(request, response);
        } catch (Exception e) {
        	throw e;
        } finally {
            log.info("■ doFilter O.");
        }
    }

    @Override
    public void destroy() {
        // Cleanup code if necessary
    	log.info("■ destroy ...");
    }
}
