package com.human.cafe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 해당 클래스는 cafe 프로젝트의 컨트롤러 클래스
 *	".cafe"로 요청되는 경우 응답 문서를 선택해서 응답해주는 컨트롤러
 * @author 	허광혁
 * @since	2024.05.31
 * @version	v.1.0
 * 			
 * 			작업이력 ]
 * 				2024.05.31	:	[ 담당자 ] 허광혁
 * 								컨트롤러 제작
 * 								"/main.cafe" URL 매핑
 */
@Controller
public class MainController {
	@RequestMapping("/main.cafe")
	public String getMain() {
		return "main";
	}
	
	
}
