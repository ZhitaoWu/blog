package com.wzt.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create By coder_tao on 2020/3/29 0029
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // session是服务器端的一个域对象，与浏览器无关，session默认30分钟(从你不操作这个服务器端的资源开始算)销毁
        if (request.getSession().getAttribute("user") == null) {
            // 响应用户请求，对网页进行重定向，网址后面加上 -> 参数
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
