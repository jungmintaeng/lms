package com.cafe24.lms.controller;

import com.cafe24.lms.service.UserService;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.lms.domain.User;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(@ModelAttribute User user) {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(
            @ModelAttribute @Valid User user,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return "user/join";
        }
        userService.join(user);
        return "user/joinsuccess";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @Auth
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(
            @AuthUser User user,
            Model model
    ){
        model.addAttribute("authUserInfo", user);
        return "user/modify";
    }

    @Auth
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(
            @ModelAttribute User user
    ){
        userService.modify(user);
        return "user/modify";
    }
}
