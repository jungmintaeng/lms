package com.cafe24.lms.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	//private Log LOG = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public void HandlerException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e) throws Exception{
		e.printStackTrace();
		request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(request, response);
	}
}
