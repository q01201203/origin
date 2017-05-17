package com.origin.core.controller;

import com.origin.core.service.AppMoneyDetailService;
import com.origin.core.util.Constants;
import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller
 * 
 * @author Joe
 */
@Controller
@RequestMapping("/app")
public class AppMoneyDetailController {

	Logger log = LoggerFactory.getLogger(AppMoneyDetailController.class);
	@Autowired
	private AppMoneyDetailService appMoneyDetailService;

	@RequestMapping("/user/borrowMoney")
	public Object borrowMoney(HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(request, Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();


		return tokenValidResult;
	}
}