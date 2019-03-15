package com.springboot.market.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@WebFilter(filterName = "CharsetFilter", urlPatterns = "/*")
public class CharsetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        filterChain.doFilter(new CharsetWrapper(req),resp);
    }

    class CharsetWrapper extends HttpServletRequestWrapper {

        HttpServletRequest resp = null;

        public CharsetWrapper(HttpServletRequest request) {
            super(request);
            resp = request;
        }

        @Override
        public String getParameter(String name) {
            String value = resp.getParameter(name);
            if (value == null || value.isEmpty()) {
                return "";
            }
            if (this.resp.getMethod().equals("get")) { // 当使用GET方式传值得时候进行编码转换
                try {
                    value = new String(value.getBytes("ISO-8859-1"), "UTF8");
                } catch (UnsupportedEncodingException e) {
                    log.debug(e.getMessage());
                }
            }
            return value;
        }
    }
}
