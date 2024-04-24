package com.goodjob.infra.naverlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaverLoginService {
	
	@Autowired
	NaverLoginDao dao;
	
	// 로그인 id 확인
	public NaverLoginDto selectOneLogin(NaverLoginDto dto) {
		return dao.selectOneLogin(dto);
	};
	
	// 회원등록
	public int insert(NaverLoginDto dto) {
		return dao.insert(dto);
	};
}
