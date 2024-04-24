package com.goodjob.infra.naverlogin;

import com.goodjob.infra.member.MemberDto;

public class NaverLoginDto extends MemberDto {
	// 네이버 사용자 정보
	private String id;
	private String email;
	private String mobile;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
