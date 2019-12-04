package com.lanswon.estate.interceptor;


import com.lanswon.commons.web.wrapper.BodyReaderHttpServletRequestWrapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 合同Intercepter
 *
 * @author jaswine
 */
public class DealIntercepter implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
		return false;
	}
}
