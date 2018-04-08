package com.community.filter;

import com.community.enums.UserRoleConstant;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Integer role = (Integer) httpServletRequest.getSession().getAttribute("role");
        if (UserRoleConstant.ADMIN.getId().equals(role)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect("/community/error.html");
        }
    }
}
