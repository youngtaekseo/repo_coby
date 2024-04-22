package com.goodjob.infra.naverlogin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class NaverLoginController {
	// 로그인화면
    @RequestMapping(value="/naverlogin")
	public String naverlogin() {
    	return "naverlogin/naverLogin";
    }
    
	@RequestMapping(value="/naver_login")
	public String naver_login(HttpServletRequest request) {
	    String client_id = ""; //[CLIENT ID];  <------------------------------------ 수정
	    String redirect_uri = ""; //{contextPath}"/naver_redirect";  <------------------------------------ 수정
	    String state = ""; //RandomStringUtils.randomAlphabetic(10);   // 랜덤 문자열 생성  <------------------------------------ 수정
	    String login_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
	            + "&client_id=" + client_id
	            + "&redirect_uri=" + redirect_uri
	            + "&state=" + state;

	    request.getSession().setAttribute("state", state);

	    return "redirect:" + login_url;
	}
	
	@RequestMapping(value="/naver_redirect")
	public String naver_redirect(HttpServletRequest request) {
		// 네이버에서 전달해준 code, state 값 가져오기
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");

	    // 세션에 저장해둔 state값 가져오기
	    String session_state = String.valueOf(request.getSession().getAttribute("state"));

		// CSRF 공격 방지를 위해 state 값 비교
	    if (!state.equals(session_state)) {
	        System.out.println("세션 불일치");
	        request.getSession().removeAttribute("state");
	        return "/error";
	    }

	    String tokenURL = "https://nid.naver.com/oauth2.0/token";
	    String client_id = ""; //[CLIENT ID];  <------------------------------------ 수정
	    String client_secret = ""; //[CLIENT SECRET];  <------------------------------------ 수정

	    // body data 생성
	    MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
	    parameter.add("grant_type", "authorization_code");
	    parameter.add("client_id", client_id);
	    parameter.add("client_secret", client_secret);
	    parameter.add("code", code);
	    parameter.add("state", state);

	    // request header 설정
	    HttpHeaders headers = new HttpHeaders();
	    // Content-type을 application/x-www-form-urlencoded 로 설정
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    // header 와 body로 Request 생성
	    HttpEntity<?> entity = new HttpEntity<>(parameter, headers);

	    try {
	        RestTemplate restTemplate = new RestTemplate();
	        // 응답 데이터(json)를 Map 으로 받을 수 있도록 관련 메시지 컨버터 추가
	        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	        // Post 방식으로 Http 요청
	        // 응답 데이터 형식은 Hashmap 으로 지정
	        ResponseEntity<HashMap> result = restTemplate.postForEntity(tokenURL, entity, HashMap.class);
	        Map<String, String> resMap = result.getBody();

	        // 리턴받은 access_token 가져오기
	        String access_token = resMap.get("access_token");

	        String userInfoURL = "https://openapi.naver.com/v1/nid/me";
	        // Header에 access_token 삽입
	        headers.set("Authorization", "Bearer "+access_token);

	        // Request entity 생성
	        HttpEntity<?> userInfoEntity = new HttpEntity<>(headers);

	        // Post 방식으로 Http 요청
	        // 응답 데이터 형식은 Hashmap 으로 지정
	        ResponseEntity<HashMap> userResult = restTemplate.postForEntity(userInfoURL, userInfoEntity, HashMap.class);
	        Map<String, String> userResultMap = userResult.getBody();

	        //응답 데이터 확인
	        System.out.println(userResultMap);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

			// 세션에 저장된 state 값 삭제
	    request.getSession().removeAttribute("state");

	    return "/sns/sns_result";
	}
}
