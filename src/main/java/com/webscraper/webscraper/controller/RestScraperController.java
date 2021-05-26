package com.webscraper.webscraper.controller;

import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webscraper.webscraper.model.Region;

@RestController
@RequestMapping("/api")
public class RestScraperController {
	
	@GetMapping("/xkcd")
	public String welcome() {
		String resp = "";
		
		try {
			Document doc = Jsoup.connect("https://c.xkcd.com/random/comic/").get();
			String xkcdSrc = "http:"+doc.select("img").get(2).attr("src");
			resp = String.format("<html><img src=\"%s\"/></html>", xkcdSrc);
		}
		catch(Exception e) {
			return "Error\n"+e;
		}
		return resp;
	}
	
	@PostMapping("/weather")
	public Region getWeatherDetails(@RequestBody Region region) {
		String country = region.getCountry();
		String city = region.getCity();
		String url = String.format("https://www.timeanddate.com/weather/%s/%s", country, city);
		
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String details = doc.select("div[id=qlook]").text();
			details.replace(".", "\n");
			region.setWeatherDetails(details);
		}
		catch(Exception e) {
			region.setWeatherDetails(e.toString());
		}
		
		return region;
	}
}
