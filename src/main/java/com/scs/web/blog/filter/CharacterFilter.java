package com.scs.web.blog.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author suyuxi
 * @className CharacterFilter
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
@WebFilter(urlPatterns = "/*")
public class CharacterFilter implements Filter{
    private static Logger logger = LoggerFactory.getLogger(CharacterFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("字符集过滤器初始化");
    }

    @Override
    public void destroy() {
        logger.info("字符集过滤器销毁");
    }
}
