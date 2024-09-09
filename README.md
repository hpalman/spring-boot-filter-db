ChatGPT Q>
Show me a full project source example To write data to a database using a Spring Boot filter

A>
Below is a simple Spring Boot project that demonstrates writing data to a database using a filter. The example uses the H2 in-memory database and JPA for persistence.

1. pom.xml
The pom.xml file includes dependencies for Spring Boot, JPA, H2, and other necessary components.
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>spring-boot-filter-db</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>spring-boot-filter-db</name>
    <description>Demo project to write data to DB using filter in Spring Boot</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <dependencies>
        <!-- Spring Boot Starter Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Starter Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- H2 Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Spring Boot Starter Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
2. Application Properties
Configure H2 in-memory database and JPA properties in the application.properties file.

properties
코드 복사
# H2 Database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true

# JPA and Hibernate configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
3. Main Application Class
The main class to bootstrap the Spring Boot application.

java
코드 복사
package com.example.filterdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootFilterDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFilterDbApplication.class, args);
    }

}
4. Entity Class
An entity representing a simple table to store data.

java
코드 복사
package com.example.filterdb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String data;

    // Constructors, getters, and setters
    public LogEntry() {}

    public LogEntry(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
5. Repository Interface
A JPA repository to handle database operations.

java
코드 복사
package com.example.filterdb.repository;

import com.example.filterdb.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}
6. Service Class
A service layer to interact with the repository.

java
코드 복사
package com.example.filterdb.service;

import com.example.filterdb.entity.LogEntry;
import com.example.filterdb.repository.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogEntryService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    public void saveLogEntry(String data) {
        LogEntry logEntry = new LogEntry(data);
        logEntryRepository.save(logEntry);
    }
}
7. Filter Class
A custom filter that extracts data from the request and saves it to the database using the service.

java
코드 복사
package com.example.filterdb.filter;

import com.example.filterdb.service.LogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
8. Controller Class
A simple REST controller to handle HTTP requests.

java
코드 복사
package com.example.filterdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @GetMapping("/log")
    public String logData() {
        return "Data logged successfully!";
    }
}
9. Filter Configuration
If needed, you can manually configure the filter with a specific URL pattern.

java
코드 복사
package com.example.filterdb.config;

import com.example.filterdb.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/log/*");  // Apply filter to specific URLs

        return registrationBean;
    }
}
10. Testing the Project
Run the Spring Boot application.
Visit http://localhost:8080/h2-console to check the H2 database.
Use the URL http://localhost:8080/log?data=YourDataHere to trigger the filter and log the data to the database.
You should see the logged data in the LogEntry table in the H2 console.
This project demonstrates how to capture data from HTTP requests using a Spring Boot filter and write that data to a database via the service and repository layers.

# 수정사항
@Configuration
public class FilterConfig {

    // @Bean
    FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/log/*");  // Apply filter to specific URLs

        return registrationBean;
    }
}
