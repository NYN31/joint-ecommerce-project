package com.jointproject.ecommerce.be.configuration.filter;

import com.google.gson.Gson;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.service.JwtTokenService;
import com.jointproject.ecommerce.be.utility.CreateObject;
import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final JwtTokenService jwtTokenService;
    private final CreateObject createObject;

    private final static String AUTHORIZATION_HEADER = "Authorization";

    public RequestFilter(JwtTokenService jwtTokenService,
                         CreateObject createObject) {
        this.jwtTokenService = jwtTokenService;
        this.createObject = createObject;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)  servletResponse;

        String token = request.getHeader(AUTHORIZATION_HEADER);
        try{
            log.info("Enter into Filter for verifying token");
            jwtTokenService.verify(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e){
            log.info("Token not verified");
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(ResultStatus.UNAUTHORIZED.getValue());
            commonResponse.setResultStatus(ResultStatus.UNAUTHORIZED);

            Gson gson = new Gson();
            String obj1 = gson.toJson(createObject.createResultResponse(commonResponse));
            log.info("Json object for response: {}", obj1);

            response.setStatus(ResultStatus.UNAUTHORIZED.getValue());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(obj1);
            return;
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
