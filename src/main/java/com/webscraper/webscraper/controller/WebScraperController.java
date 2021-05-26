package com.webscraper.webscraper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebScraperController {
	
	@RequestMapping(value="/weather")
	public String home() {
		return "weather";
	}
}
