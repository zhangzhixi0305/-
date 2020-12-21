package com.zhixi.filter;


import com.zhixi.pojo.User;
import com.zhixi.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 判断是否退出登录，拦截过滤器
 */
public class SysFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //强转为Http的请求以及相应,操作session和重定向操作
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp = (HttpServletResponse) response;
        //拿到这个session对象进行判断
        User userSession = (User) rq.getSession().getAttribute(Constants.USER_SESSION);
        if (null == userSession) {
            //为空表示session已经被移除（在LogoutServlet中有移除操作）,或者未登录
            rp.sendRedirect(rq.getContextPath() + "/error.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
