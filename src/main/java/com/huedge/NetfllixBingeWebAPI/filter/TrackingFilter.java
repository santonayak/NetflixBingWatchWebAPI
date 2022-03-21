package com.huedge.NetfllixBingeWebAPI.filter;

import com.huedge.NetfllixBingeWebAPI.service.TimingMiddleware;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TrackingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        TimingMiddleware timingMiddleware = new TimingMiddleware();
        timingMiddleware.start();
        boolean flag = validateRequest(request);

        if(flag == false){
            response.setStatus(401);
            Long value = timingMiddleware.end();
            response.setHeader("X-TIME-TO-EXECUTE",value.toString());
            return;
        }
        request.setAttribute("timer", timingMiddleware);
        filterChain.doFilter(request, response);

    }


    public boolean validateRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Auth-Token");
        System.out.println("auth token is "+ header);
        if (header == null || header.length() == 0) {
            return false;
        }
        return true;

    }
}
