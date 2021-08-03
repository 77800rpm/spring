package com.kh.spring.member.model.service;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.vo.Member;

@Service("service")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberDAO dao;
		
	@Override
	public Member login(Member m) {
		
		return dao.login(sqlSession, m);
	}

	@Override
	public int insertMember(Member m) {
		return dao.insertMember(sqlSession, m);
	}

	@Override
	public int updateMember(Member m) {

		return dao.updateMember(sqlSession, m);
		
	}

	@Override
	public int updatePwd(HashMap<String, String> map) {
		return dao.updatePwd(sqlSession, map);
		
	}

	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return dao.deleteMember(sqlSession, id);
	}

	@Override
	public int duplicateId(String userId) {
		// TODO Auto-generated method stub
		return dao.duplicateId(sqlSession, userId);
	}
	
}
