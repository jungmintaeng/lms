package com.cafe24.security;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ("".equals(email) || "".equals(password)) {
            request.setAttribute("result", "fail");
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
            return false;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        // userService 주입하기 위해 UserService를 뽑는 일
        ApplicationContext ac =
                WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        UserService service = ac.getBean(UserService.class);
        User authUser = service.findUserByEmailAndPassword(user);
        if (authUser == null) {
            request.setAttribute("result", "fail");
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
            return false;
        }

        HttpSession session = request.getSession();
        session.setAttribute("authUser", authUser);
        response.sendRedirect(request.getContextPath() + "/main");
        return false;
    }
}
