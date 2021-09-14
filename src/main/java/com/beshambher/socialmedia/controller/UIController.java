package com.beshambher.socialmedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

	/**
	 * Forwarding URL for Angular routes with prefix /in/*, /contact and /about
	 * UI requests forward to resources
	 */
	@GetMapping(value = { "/in/**", "/contact", "/about" })
	public String forward() {
		return "forward:/";
	}
}
