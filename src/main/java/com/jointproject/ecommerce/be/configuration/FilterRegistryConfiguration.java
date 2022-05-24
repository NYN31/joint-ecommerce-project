package com.jointproject.ecommerce.be.configuration;

import com.jointproject.ecommerce.be.configuration.filter.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistryConfiguration {
    @Autowired
    private RequestFilter requestFilterNew;

    @Bean
    public FilterRegistrationBean<RequestFilter> registerRequestFilter(){
        FilterRegistrationBean<RequestFilter> requestFilter = new FilterRegistrationBean<>();
        requestFilter.setFilter(requestFilterNew);
        requestFilter.addUrlPatterns("/buyer/test");
        return requestFilter;
    }
}
