package com.goodjob.infra.member;

import java.util.Date;

public class MemberDto {
	
	private String  mbrSeq;
	private Integer mbrType; // 1:관리자, 2:사용자
	private String  mbrName;
	private Integer mbrSex;
	private String  mbrBirthday;
	private String  mbrEmail;
	private String  mbrPassword;
	private Date    mbrRegDt;
	private Date    mbrUdtDt;
	private Integer mbrDelNy; // 0:미삭제, 1:삭제
	
	private Integer xmbrCount;
	private String  xmbrPwConfirm;
	private String  xmbrPasswordPre;
	private Integer xrowSeq;
	
//	===================================
	
	public String getMbrSeq() {
		return mbrSeq;
	}
	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
	public Integer getMbrType() {
		return mbrType;
	}
	public void setMbrType(Integer mbrType) {
		this.mbrType = mbrType;
	}
	public String getMbrName() {
		return mbrName;
	}
	public void setMbrName(String mbrName) {
		this.mbrName = mbrName;
	}
	public Integer getMbrSex() {
		return mbrSex;
	}
	public void setMbrSex(Integer mbrSex) {
		this.mbrSex = mbrSex;
	}
	public String getMbrBirthday() {
		return mbrBirthday;
	}
	public void setMbrBirthday(String mbrBirthday) {
		this.mbrBirthday = mbrBirthday;
	}
	public String getMbrEmail() {
		return mbrEmail;
	}
	public void setMbrEmail(String mbrEmail) {
		this.mbrEmail = mbrEmail;
	}
	public String getMbrPassword() {
		return mbrPassword;
	}
	public void setMbrPassword(String mbrPassword) {
		this.mbrPassword = mbrPassword;
	}
	public Date getMbrRegDt() {
		return mbrRegDt;
	}
	public void setMbrRegDt(Date mbrRegDt) {
		this.mbrRegDt = mbrRegDt;
	}
	public Date getMbrUdtDt() {
		return mbrUdtDt;
	}
	public void setMbrUdtDt(Date mbrUdtDt) {
		this.mbrUdtDt = mbrUdtDt;
	}
	public Integer getMbrDelNy() {
		return mbrDelNy;
	}
	public void setMbrDelNy(Integer mbrDelNy) {
		this.mbrDelNy = mbrDelNy;
	}
	public Integer getXmbrCount() {
		return xmbrCount;
	}
	public void setXmbrCount(Integer xmbrCount) {
		this.xmbrCount = xmbrCount;
	}
	public String getXmbrPasswordPre() {
		return xmbrPasswordPre;
	}
	public void setXmbrPasswordPre(String xmbrPasswordPre) {
		this.xmbrPasswordPre = xmbrPasswordPre;
	}
	public String getXmbrPwConfirm() {
		return xmbrPwConfirm;
	}
	public void setXmbrPwConfirm(String xmbrPwConfirm) {
		this.xmbrPwConfirm = xmbrPwConfirm;
	}
	public Integer getXrowSeq() {
		return xrowSeq;
	}
	public void setXrowSeq(Integer xrowSeq) {
		this.xrowSeq = xrowSeq;
	}
	
}
