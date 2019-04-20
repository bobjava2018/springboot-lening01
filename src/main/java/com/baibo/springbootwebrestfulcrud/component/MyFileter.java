package com.baibo.springbootwebrestfulcrud.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Locale;

@Component
public class MyFileter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1=(HttpServletRequest) request;
        System.out.println("接受的参数--》》》》》》》》》》》》"+request.getParameter("_method"));

        HttpServletRequest requestToUse = (HttpServletRequest) request;

        if ("POST".equals(requestToUse.getMethod()) && request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) == null) {
            String paramValue = request.getParameter("_method");
            if (StringUtils.hasLength(paramValue)) {
                String method = paramValue.toUpperCase(Locale.ENGLISH);
                 System.out.println("method   00000000>>>"+method);
                    requestToUse = new HttpMethodRequestWrapper(requestToUse,method);

            }
        }


        chain.doFilter(requestToUse, response);
    }
    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }
    @Override
    public void destroy() {

    }
}
