package com.example.filterdb.filter;

import com.example.filterdb.service.LogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// @ChatGPT 답 테스트
@Component
public class LogFilter implements Filter {

    @Autowired
    private LogEntryService logEntryService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if necessary
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestData = httpRequest.getParameter("data");

        if (requestData != null) {
            logEntryService.saveLogEntry(requestData);  // Save to DB
        }

        // Continue with the request
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code if necessary
    }
}
