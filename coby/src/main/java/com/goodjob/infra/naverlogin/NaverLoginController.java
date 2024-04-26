package com.goodjob.infra.naverlogin;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class NaverLoginController {
	@Autowired
	NaverLoginService service;
	
	@Value("${naver_client_id}")
    private String naverClientId;	
	
	// 로그인화면
    @RequestMapping(value="/naverLogin")
	public String naverLogin() {
    	return "naverlogin/naverLogin";
    }
    
	// 로그인화면
    @RequestMapping(value="/callback")
	public String callback() {
    	return "naverlogin/callback";
    }
    
	// 로그인화면
    @RequestMapping(value="/naverLoginResult")
	public String naverLoginResult() {
    	return "naverlogin/naverLoginResult";
    }
    
	@RequestMapping(value="/loginNaver")
	public String loginNaver(HttpServletRequest request) {
	    //String client_id    = naverClientId;
	    String redirect_uri = "http://localhost:8081/redirectNaver";
	    //String state = RandomStringUtils.randomAlphabetic(10);   // 랜덤 문자열 생성
	    // 랜덤 문자열 생성
	    //=====================================================================
	    int leftLimit  = 48;  // '0'
	    int rightLimit = 122; // 'z'
	    int stringLength = 10; // 문자열길이
	    Random random = new Random();
	    
	    String state = random.ints(leftLimit, rightLimit+1)
	    		.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	    		.limit(stringLength)
	    		.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	    		.toString();
	    //=====================================================================
	    
	    String login_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
	            + "&client_id=" + naverClientId
	            + "&redirect_uri=" + redirect_uri
	            + "&state=" + state;

	    request.getSession().setAttribute("state", state);
	    
	    return "redirect:" + login_url;
	}
	
	@RequestMapping(value="/redirectNaver")
	public String naverRedirect(HttpServletRequest request, NaverLoginDto dto, NaverLoginDto isDto, Model model) {
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

	    String tokenURL      = "https://nid.naver.com/oauth2.0/token";
	    String client_id     = "r1aTHRBql6Ny_r2b60bZ";
	    String client_secret = "tDeVoy1gLD";

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
	        Map<String, Object> userResultMap = userResult.getBody();
	        
	        //응답 데이터 확인
	        System.out.println(userResultMap);
	        
		    /*{response={
		    id=YosQCAcb_GcHkIxD9bfiJe7HgRnf_Fm8vH7dtHmL74U, 
		    email=ramses69@naver.com, 
		    mobile=010-3540-6062, 
		    mobile_e164=+821035406062, 
		    name=서영택}, 
		    resultcode=00, 
		    message=success}
			*/
	        
	        Map<String, String> response = (Map<String, String>) userResultMap.get("response");
	        dto.setEmail(response.get("email"));

	        // 회원존재확인
	        isDto = service.selectOneLogin(dto);
	        
	        if(isDto == null) {
	        	dto.setId(response.get("id"));
	        	dto.setMbrType(1); // 사용자
	        	dto.setEmail(response.get("email"));
	        	dto.setMobile(response.get("mobile"));
	        	dto.setName(response.get("name"));
	        	
	        	service.insert(dto);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

		// 세션에 저장된 state 값 삭제
	    request.getSession().removeAttribute("state");
	    
	    return "/naverlogin/naverLoginResult";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insert")
	public Map<String, Object> insert(NaverLoginDto dto, NaverLoginDto isDto) {
		Map<String, Object> returnMap = new HashMap<>();

		System.out.println("dto.getEmail()===================" + dto.getEmail());
		
        // 회원존재확인
		isDto = service.selectOneLogin(dto);
        
        if(isDto == null) {
        	dto.setMbrType(1); // 사용자
        	
    		if(service.insert(dto) == 1) {
    			returnMap.put("rt", "success");
    		} else {
    			returnMap.put("rt", "fail");
    		}
        } else {
        	returnMap.put("rt", "success");
        }
        
		return returnMap;
	}
}
