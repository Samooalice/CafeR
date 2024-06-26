package com.human.cafe.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.human.cafe.vo.MemberVO;

public class MemberDao {
	@Autowired
	SqlSessionTemplate session;
	
	// 로그인 SQL 보내기
	public int getLogin(MemberVO mVO) {
		return session.selectOne("mSQL.login", mVO);
	}
	
	public int idCheck(String id) {
		return session.selectOne("mSQL.idCheck", id);
	}
	
	public int addMember(MemberVO mVO) {
		return session.insert("mSQL.addMember", mVO);
	}
}
