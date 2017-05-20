package com.lawren.bysj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lawren.bysj.pojo.Weacher;
import com.lawren.bysj.tool.ParseWeatherTool;

@Controller
public class OtherController {
	private static final String WEATHER_URL="http://api.jirengu.com/weather.php?city=";
	@Autowired
	private ParseWeatherTool parseWeatherTool;
	@ResponseBody
	@RequestMapping("getweather")
	public Weacher getWeacher(String city){
		String json_weather =parseWeatherTool.getWeatherData(WEATHER_URL,city);
		return parseWeatherTool.jsonTOWeather(json_weather);
	}
	
}
