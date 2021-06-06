package com.webscraper.webscraper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebScraperController {

	@RequestMapping(value="/")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/directions")
	public String directions() {
		return "directions";
	}
	
	@RequestMapping(value="/weather")
	public String weather() {
		return "weather";
	}

	@RequestMapping(value="/xkcd")
	public String xkcd() {
		return "xkcd";
	}
}
