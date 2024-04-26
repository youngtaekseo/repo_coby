package com.goodjob.infra.kakaologin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KakaoLoginController {
	@Value("${kakao_rest_key}")
	private String kakaoRestKey;
	
	@Value("${kakao_redirect_uri}")
	private String kakaoRedirectUri;	
	
	@Autowired
	KakaoLoginService service;
	
	// 로그인화면
    @RequestMapping(value="/kakaoLogin")
	public String kakaoLogin(Model model) throws Exception {
    	String location = "https://kauth.kakao.com/oauth/authorize?client_id="+kakaoRestKey+"&redirect_uri="+kakaoRedirectUri+"&response_type=code&scope=account_email,name,gender";
    	model.addAttribute("location", location);
    	model.addAttribute("kakaoRestKey", kakaoRestKey);
    	model.addAttribute("kakaoRedirectUri", kakaoRedirectUri);
    	//System.out.println(location);
    	
    	return "kakaologin/kakaoLogin";
    }
    
    @RequestMapping(value="/loginKakaoRedirect")
    public String loginKakaoRedirect(KakaoLoginDto dto, Model model) throws Exception {
    	System.out.println("dto.getCode()================"+dto.getCode());
		
    	// 토큰 받기 
    	String accessToken = service.getAccessTokenFromKakao(kakaoRestKey, dto.getCode());
		  
    	dto = service.getUserInfo(accessToken, dto);
        System.out.println("id : " + dto.getId());
        System.out.println("name : " + dto.getName());
        System.out.println("email : " + dto.getEmail());
        System.out.println("gender : " + dto.getGender());
		  
        model.addAttribute("info", dto);
        
        return "kakaologin/kakaoLoginCallBack";
    }
    
}
