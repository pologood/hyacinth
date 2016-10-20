/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.jd.hyacinth.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 * @author J-ONE
 * @since 2016-10-20
 */
@Controller
@RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
public class IndexController{
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String index(HttpServletRequest request,Model view){
	    view.addAttribute("contextPath", request.getContextPath());
		return "mainFrame/frame";
	}
}
