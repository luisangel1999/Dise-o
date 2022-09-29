package com.edudemic.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String inicio(){
		return "inicio";
	}
	@GetMapping("/landing")
	public String land(){
		return "landing/landing_page";
	}
	@GetMapping("/nosotros")
	public String nosotros(){
		return "landing/nosotros";
	}
	@GetMapping("/admin")
	public String admin(){
		return "redirect:/private/index";
	}

}
