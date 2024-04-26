package com.goodjob.infra.kakaologin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KakaoLoginController {
	// 로그인화면
    @RequestMapping(value="/kakaoLogin")
	public String kakaoLogin() throws Exception {
    	return "kakaologin/kakaoLogin";
    }
}
