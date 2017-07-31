package com.origin.core.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RememberAuthenticationFilter extends FormAuthenticationFilter {

	Logger log = LoggerFactory.getLogger(RememberAuthenticationFilter.class);
	/**
	 * 判断是否让用户登陆
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		log.debug("renxinhua isAccessAllowed");
		Subject subject = getSubject(request, response);
		
		return subject.isAuthenticated() || subject.isRemembered();
	}

}
