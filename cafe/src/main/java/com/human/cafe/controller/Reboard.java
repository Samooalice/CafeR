package com.human.cafe.controller;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.human.cafe.vo.ReboardVO;


/**
 * 해당 클래스는 댓글 게시판 관련 요청을 처리해주는 컨트롤러 클래스
 * @auther	허광혁
 * @since	2024.06.30
 * @version	v.1.0
 * 			
 * 			작업이력 ]
 * 				2024.06.03 - 담당자 : 허광혁
 * 								리스트 폼보기 요청
 * 								글쓰기 폼보기 요청
 */
@Controller
@RequestMapping("/reboard")
public class Reboard {
	@RequestMapping("/reboard.cafe")
	public ModelAndView getReboard(HttpSession session, ModelAndView mv, RedirectView rv) {
		session.setAttribute("SID", "jennie");
		mv.setViewName("reboard/reboard");
		return mv;
	}
	
	@RequestMapping("/reboardWrite.cafe")
	public ModelAndView reboardWrite(HttpSession session, ModelAndView mv, RedirectView rv, 
										String nowPage, ReboardVO rVO) {
		
		mv.addObject("NOWPAGE", nowPage);
		mv.addObject("DATA", rVO);
		mv.setViewName("reboard/reboardWrite");
		return mv;
	}
}

