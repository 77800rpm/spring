package com.kh.spring.member.model.service;

import java.util.HashMap;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	Member login(Member m);

	int insertMember(Member m);

	int updateMember(Member m);

	int updatePwd(HashMap<String, String> map);

	int deleteMember(String id);

	int duplicateId(String userId);


}
