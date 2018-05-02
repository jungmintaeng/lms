package com.cafe24.lms.controller;

import com.cafe24.lms.domain.Reservation;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.dto.JSONResult;
import com.cafe24.lms.dto.PageDto;
import com.cafe24.lms.dto.ReservationDto;
import com.cafe24.lms.service.MainService;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;

	@RequestMapping( { "", "/main" } )
	public String index(
			Model model,
			@RequestParam(value = "p", defaultValue = "1") Integer page
	) {
		mainService.insertAdmin();
		model.addAttribute("list", new PageDto<>(mainService.findAllItems(page), page));
		return "main/index";
	}

	@Auth
	@RequestMapping(value = "/rent/{itemNo}")
	public String rent(
			@AuthUser User user,
			@PathVariable("itemNo") Long itemNo,
			@RequestParam("p") Integer page,
			Model model
	) {
		if(mainService.rent(user.getNo(), itemNo) == false){
			model.addAttribute("list", new PageDto<>(mainService.findAllItems(page), page));
			model.addAttribute("result", "fail");
			return "main/index";
		}
		return "main/rent";
	}

	@Auth
	@RequestMapping(value = "/reserve/{itemNo}")
	@ResponseBody
	public JSONResult reserve(
			@AuthUser User user,
			@PathVariable("itemNo") Long itemNo
	){
		Reservation reservation = mainService.reserve(user.getNo(), itemNo);
		return reservation == null ?
				JSONResult.success(null) :
				JSONResult.success(new ReservationDto(reservation));
	}
}
