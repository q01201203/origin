package com.origin.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class IndexController {
	
	@RequestMapping("/index")
	public String login(){
		return "index";
	}

}
