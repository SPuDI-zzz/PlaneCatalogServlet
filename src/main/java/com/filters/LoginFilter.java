package com.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/user/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException,
            ServletException {
        HttpServletRequest httpServletReq = (HttpServletRequest) req;
        Object logged = httpServletReq.getSession().getAttribute("logged");

        if (logged != null && (boolean) logged) {
            fc.doFilter(req, resp);
            return;
        }
        HttpServletResponse httpServletResp = (HttpServletResponse) resp;
        httpServletResp.sendRedirect(httpServletReq.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
    }
}
