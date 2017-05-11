package com.origin.core.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RememberAuthenticationFilter extends FormAuthenticationFilter {
	
	/**
	 * 判断是否让用户登陆
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		System.out.println("lic RememberAuthenticationFilter1111111111");
		Subject subject = getSubject(request, response);
		
		return subject.isAuthenticated() || subject.isRemembered();
	}

}
