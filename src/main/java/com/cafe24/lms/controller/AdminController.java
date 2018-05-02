package com.cafe24.lms.controller;

import com.cafe24.lms.domain.Rental;
import com.cafe24.lms.dto.PageDto;
import com.cafe24.lms.service.AdminService;
import com.cafe24.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Auth(role = Auth.Role.ADMIN)
@Controller
@RequestMapping( "/admin" )
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping( { "", "/rent", "/main" } )
	public String rent(
			Model model,
			@RequestParam(value = "p", defaultValue = "1") Integer page
	) {
		model.addAttribute("list", new PageDto<>(adminService.findRental(page), page));
		return "admin/rent";
	}
	
	@RequestMapping( "/reserve" )
	public String reserve(
			Model model,
			@RequestParam(value = "p", defaultValue = "1") Integer page
	) {
		model.addAttribute("list", new PageDto<>(adminService.findReservation(page), page));
		return "admin/reserve";
	}
	
}
