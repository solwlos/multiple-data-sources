package com.yangs.architecture.filter;

import com.yangs.architecture.config.DataSourceContextHolder;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import java.io.IOException;

/**
 * @author sol
 * @date 2023/10/17 10:39
 * @Version 1.0
 */
@Component
@WebFilter(urlPatterns = {"/**"},filterName = "pathFilter")
public class urlFilter  implements Filter {
        /**
         * 自定义Filter
         * 对请求的header 过滤token
         *
         * 过滤器Filter可以拿到原始的HTTP请求和响应的信息，
         *     但是拿不到你真正处理请求方法的信息，也就是方法的信息
         *
         * @Component 注解让拦截器注入Bean，从而让拦截器生效
         * @WebFilter 配置拦截规则
         *
         * 拦截顺序：filter—>Interceptor-->ControllerAdvice-->@Aspect -->Controller
         */
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("TokenFilter init " + filterConfig.getFilterName());
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

            SecurityContextHolderAwareRequestWrapper requestWrapper;
            if(request instanceof StandardMultipartHttpServletRequest) {
                requestWrapper = (SecurityContextHolderAwareRequestWrapper)
                        ((StandardMultipartHttpServletRequest) request).getRequest();
            } else{
                requestWrapper = (SecurityContextHolderAwareRequestWrapper)request;
            }
            String path = requestWrapper.getServletPath();
            System.out.println(path.toString());

            try {
                if(path.indexOf("/yuzhai") > -1){

                    path = path.replaceAll("/yuzhai","");
                    DataSourceContextHolder.setDataSource("YangsDataSource");
                    requestWrapper.getRequestDispatcher(path).forward(request,response);

                }else if(path.indexOf("/sol") > -1){

                    path = path.replaceAll("/sol","");
                    DataSourceContextHolder.setDataSource("TestDataSource");
                    requestWrapper.getRequestDispatcher(path).forward(request,response);
                }
            }catch (Exception e){
                System.out.println("切换数据源失败 ！！！" + e.getMessage());
            }
            chain.doFilter(request,response);
        }


        @Override
        public void destroy() {
            System.out.println("TokenFilter destroy");
        }


}
