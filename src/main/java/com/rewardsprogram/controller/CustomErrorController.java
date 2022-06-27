package com.rewardsprogram.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling error messages.
 * 
 * @author Manasa
 *
 */

@Controller
public class CustomErrorController {

	@GetMapping("/403")
	public String forbidden(Model model) {
		return "error/403.html";
	}

	@GetMapping("/404")
	public String notFound(Model model) {
		return "error/404.html";
	}
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "error/error.html";
	}
}