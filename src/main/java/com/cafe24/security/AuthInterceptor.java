package com.cafe24.security;

import com.cafe24.lms.domain.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 핸들러 종류 확인
		if ((handler instanceof HandlerMethod) == false) {
			return true;
		}
		// casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		Method method = handlerMethod.getMethod();
		if (method.getDeclaringClass().isAnnotationPresent(Auth.class)) {
			auth = handlerMethod.getBean().getClass().getAnnotation(Auth.class);
		} else if(auth == null){
			return true;
		}
		/**
		 * auth.role()을 사용하고 싶은 경우 auth.role() 값을 이용하면 된다.
		 */

		// @Auth가 붙어있는 경우
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		User vo = (User) session.getAttribute("authUser");
		if (vo == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		/**
		 * role 확인
		 */
		if(auth.role() != vo.getRole() && auth.role() == Auth.Role.ADMIN){
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}

		return true;
	}
}
