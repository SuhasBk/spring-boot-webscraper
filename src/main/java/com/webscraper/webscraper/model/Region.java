package com.webscraper.webscraper.model;

import org.springframework.stereotype.Component;

@Component
public class Region {
	
	private String city;
	private String country;
	private String weatherDetails;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getWeatherDetails() {
		return weatherDetails;
	}
	public void setWeatherDetails(String weatherDetails) {
		this.weatherDetails = weatherDetails;
	}
	
	@Override
	public String toString() {
		return "Weather [city=" + city + ", country=" + country + ", weatherDetails=" + weatherDetails + "]";
	}
}
