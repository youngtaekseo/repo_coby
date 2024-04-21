package com.goodjob.infra.kakaopay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller

public class KakaoPayController {
	@Autowired
	KakaoPayService kakaoPayService;
	
	// 결제화면
    @RequestMapping(value="/kakao")
	public String kakao() {
    	return "kakaopay/kakaoPay";
    }

    // 결제요청
    @RequestMapping(value="/kakaopay")
	public String kakaopay() {
    	System.out.println(".................................................... kakaopay");
        return "redirect:" + kakaoPayService.kakaoPayReady();
	}
    
    // 결제성공
    @RequestMapping(value="/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model, HttpSession httpSession) {
    	model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token, httpSession));
    	return "kakaopay/kakaoPaySuccess";
    }     
    
    // 결제취소
    @RequestMapping(value="/kakaopayCancel")
	public String kakaopayCancel(Model model, HttpSession httpSession ) {
    	System.out.println(".................................................... kakaopayCancel");
    	String sessTidString = (String) httpSession.getAttribute("sessTid");
    	if(sessTidString != null) {
    		model.addAttribute("info", kakaoPayService.kakaoPayCancel(httpSession));

    		// 세션삭제
        	httpSession.removeAttribute("sessTid");
        	httpSession.removeAttribute("sessTotal");
        	httpSession.removeAttribute("sessTaxFree");
        	httpSession.removeAttribute("sessVat");
        	
        	return "kakaopay/kakaopayCancelSuccess"; 
    	} else {
    		return "redirect:/kakao"; 
    	}
	}
}
