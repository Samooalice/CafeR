package com.human.cafe.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.human.cafe.dao.MemberDao;
import com.human.cafe.vo.MemberVO;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import com.sun.org.apache.bcel.internal.generic.I2F;

/**
 * 
 * @author	허광혁
 * @since	2024.06.03
 * @version	v.1.0
 * 			
 * 			작업이력 ]
 * 				2024.06.03 - 담당자 : 허광혁
 * 								로그인 폼보기 요청 처리
 * 								로그인 요청 처리
 * 								화원가입 폼보기 요청 처리
 */
@Controller
@RequestMapping("/member")
public class Member {
	@Autowired
	MemberDao mDao;
	
	/**
	 * 로그아웃 처리 함수
	 */
	@RequestMapping("/logout.cafe")
	public ModelAndView logout(HttpSession session, ModelAndView mv) {
		session.removeAttribute("SID");
		
		mv.setViewName("main");
		return mv;
	}
	
	/**
	 * 로그인 폼보기 요청 함수
	 */
	@RequestMapping("/login.cafe")
	public ModelAndView login(ModelAndView mv) {
		mv.setViewName("member/login");
		return mv;
	}

	/**
	 * 로그인 요청 처리 함수
	 */
	@RequestMapping("/loginProc.cafe")
	public ModelAndView loginProc(HttpSession session, ModelAndView mv, MemberVO mVO, RedirectView rv) {
		int cnt = mDao.getLogin(mVO);
		
		if(cnt == 0) {
			rv.setUrl("/cafe/member/login.cafe");
			mv.setView(rv);
			return mv;
		}
		
		session.setAttribute("SID", mVO.getId());
		rv.setUrl("/cafe/main.cafe");
		mv.setView(rv);
		return mv;
	}
	
	/**
	 * 회원가입 폼보기 요청 함수
	 */
	@RequestMapping("/join.cafe")
	public ModelAndView join(ModelAndView mv) {
		mv.setViewName("member/join");
		return mv;
	}
	
	/**
	 * 아이디 중복 검사 처리 함수
	 */
	@ResponseBody
	@RequestMapping("/idCheck.cafe")
	public HashMap<String, String> idCheck(String id) {
		System.out.println("################################## in");
		HashMap<String, String> map = new HashMap<String, String>();
		
		int cnt = mDao.idCheck(id);
		if(cnt == 0) {
			map.put("result", "OK");
		}else {
			map.put("result", "NO");			
		}
		
		return map;
	}
	
	@RequestMapping("/joinProc.cafe")
	public ModelAndView joinProc(HttpSession session, ModelAndView mv, RedirectView rv, MemberVO mVO) {
		int cnt = mDao.addMember(mVO);
		if(cnt != 1) {
			// 회원가입 실패
			rv.setUrl("/cafe/member/join.cafe");
			mv.setView(rv);
			return mv;
		}
		
		rv.setUrl("/cafe/member/login.cafe");
		mv.setView(rv);
		return mv;
	}
}
