package com.jd.hyacinth.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jd.hyacinth.web.controller.SeckillController;

public class LoginHandlerInterceptor implements HandlerInterceptor {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		LOGGER.info("The request uri is {}", requestUri);
		if(requestUri.indexOf("login") == -1) {
			HttpSession session = request.getSession();
			String userName = (String)session.getAttribute("userName");
			if(userName == null) {
				request.getRequestDispatcher("/html/login.html").forward(request, response);
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
