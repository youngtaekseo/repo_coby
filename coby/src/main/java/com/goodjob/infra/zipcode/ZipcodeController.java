package com.goodjob.infra.zipcode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ZipcodeController {
	
	@RequestMapping(value = "/zipcode")
	public String zipcode() {
		return "zipcode/zipcode";
	}
}
