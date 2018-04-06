package com.community.filter;

import com.community.enums.UserRoleConstant;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        Integer role = (Integer) httpServletRequest.getSession().getAttribute("role");
        Integer role = 2;   //todo 为了调试更快，需要改回来
        if (UserRoleConstant.ADMIN.getId().equals(role)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect("/community/error.html");
        }
    }
}
