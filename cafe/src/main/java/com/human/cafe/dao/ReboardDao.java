package com.human.cafe.dao;

import java.util.List;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.human.cafe.util.PageUtil;
import com.human.cafe.vo.*;

public class ReboardDao {
	@Autowired
	SqlSessionTemplate session;
	
	// 로그인 SQL 보내기
	public int addBoard(ReboardVO rVO) {
		return session.insert("rSQL.addBoard", rVO);
	}
	
	// 총 댓글 수 조회 전담 처리 함수
	public int getTotal() {
		return session.selectOne("rSQL.getTotal");
	}
	
	// 댓글 리스트 조회 전담 처리 함수
	public List getList(PageUtil page) {
		return session.selectList("rSQL.getList", page);
	}
	
	// 상위 게시글 받아오기
	public ReboardVO getUpContent(int bno) {
		return session.selectOne("rSQL.upContent", bno);
	}
	
	// 댓글 삭제 전담 처리 함수
	public int delReboard(ReboardVO rVO) {
		return session.update("rSQL.delReboard", rVO);
	}
	
	// 좋아요 증가 전담 처리 함수
	public int addGood(int bno) {
		return session.update("rSQL.addGood", bno);
	}
}
