package com.hello.forum.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroduceController {
	
	// http://localhost:8080/introduce
	
	@GetMapping("/introduce")
	// URL이 무엇을 할 것인지를 메서드 명으로 정하는 것이 좋다. (의미를 전달하는 것이 좋다.)
	public String viewIntroducePage(Model model) {
		
		model.addAttribute("me", "김정우");
		model.addAttribute("age", "28");
		model.addAttribute("city", "춘천");
		
		
		return "introduce";
	}
}
