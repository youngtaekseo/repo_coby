package com.goodjob.infra.naverlogin;

public interface NaverLoginDao {
	// 로그인 id 확인
	public NaverLoginDto selectOneLogin(NaverLoginDto dto);
	
	// 회원등록
	public int insert(NaverLoginDto dto);
}
