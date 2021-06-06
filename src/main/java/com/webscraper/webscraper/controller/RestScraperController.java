package com.webscraper.webscraper.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webscraper.webscraper.model.Region;

@RestController
@RequestMapping("/api")
public class RestScraperController {

	@Autowired
	ObjectMapper mapper;

	@GetMapping("/xkcd")
	public Map<String, String> welcome() {
		Map<String, String> resp = new HashMap<>();

		try {
			Document doc = Jsoup.connect("https://c.xkcd.com/random/comic/").get();
			resp.put("src", "http:" + doc.select("img").get(2).attr("src"));
		} catch (Exception e) {
			resp.put("error", e.toString());
		}
		return (resp);
	}
	
	@PostMapping("/weather")
	public Region getWeatherDetails(@RequestBody Region region) {
		String country = region.getCountry();
		String city = region.getCity();
		String url = String.format("https://www.timeanddate.com/weather/%s/%s", country, city);
		
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String details = doc.select("div[id=qlook]").text();
			region.setWeatherDetails(details);
		}
		catch(Exception e) {
			region.setWeatherDetails(e.toString());
		}
		
		return region;
	}

	@PostMapping("/directions")
	@SuppressWarnings("unchecked")
	public Map<String, String> getDirections(@RequestBody String data) throws Exception {
		Map<String, String> jsonData = (HashMap<String, String>) mapper.readValue(data, HashMap.class);
		String from = jsonData.get("from");
		String to = jsonData.get("to");
		String url = String.format("https://maps.google.com/maps?saddr=%s&daddr=%s", from, to);
		
		WebDriver browser = new FirefoxDriver(new FirefoxOptions().setHeadless(true).setLogLevel(FirefoxDriverLogLevel.WARN));
		browser.get(url);

		String src = ((TakesScreenshot) browser).getScreenshotAs(OutputType.BASE64);
		browser.close();

		jsonData.clear();
		jsonData.put("src", src);
		return jsonData;
	} 
}
