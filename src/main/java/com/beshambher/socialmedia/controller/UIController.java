package com.beshambher.socialmedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

	/**
	 * Forwarding URL for Angular routes with prefix /my/*, /contact and /about
	 * UI requests forward to resources
	 */
	@GetMapping(value = { "/my/**", "/contact", "/about" })
	public String forward() {
		return "forward:/";
	}
}
