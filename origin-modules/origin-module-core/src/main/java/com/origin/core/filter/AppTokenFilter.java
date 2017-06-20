package com.origin.core.filter;

import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import com.origin.core.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lc on 2017/5/12.
 */
public class AppTokenFilter implements Filter{

    private String excludedPaths;
    private String[] excludedPathsArray;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("renxinhua AppTokenFilter doFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();

        boolean isExcluded = false;
        if (excludedPathsArray!=null){
            for (String path:excludedPathsArray
                    ) {
                if (path.equals(servletPath)){
                    isExcluded = true;
                }
            }
        }
        System.out.println("renxinhua servletpath = "+ servletPath +" isExclude = "+isExcluded);
        if (isExcluded){
            filterChain.doFilter(request,servletResponse);
        }else {
            String token = request.getHeader("Authorization");
            try {
                SimpleToken simpleToken = CustomToken.parse(token);
                request.setAttribute("token",simpleToken);
                filterChain.doFilter(request,servletResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPaths = filterConfig.getInitParameter("excludedPaths");
        if (!StringUtil.isNullOrBlank(excludedPaths)){
            excludedPathsArray = excludedPaths.split(",");
        }
    }

    @Override
    public void destroy() {

    }
}
