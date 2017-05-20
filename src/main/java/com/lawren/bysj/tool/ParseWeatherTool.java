package com.lawren.bysj.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lawren.bysj.pojo.Weacher;

@Component("parseWeatherTool")
public class ParseWeatherTool {
	private  static Logger logger = Logger.getLogger(ParseWeatherTool.class);
	/***
	 * 从给定的URL获取
	 * @param urlStr
	 * @return
	 */
	public String getWeatherData(String urlStr,String city) {
		InputStreamReader inr = null;
		InputStream in = null;
		StringBuilder strBuff = new StringBuilder();
		try {
			URL url = new URL(urlStr+city);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			conn.connect();
			int code = conn.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				in = conn.getInputStream();
				inr = new InputStreamReader(in);
				char cs[] = new char[1024];
				int len = 0;
				while ((len = inr.read(cs)) != -1) {
					strBuff.append(new String(cs, 0, len));
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = JSON.parseObject(strBuff.toString());
		if (!jsonObj.getInteger("error").equals(0)) {
			return getWeatherData(urlStr, "");
		}else {
			return strBuff.toString();
		}
		
	}

	public Weacher jsonTOWeather(String weacherStr) {
		JSONObject jsonObj = JSON.parseObject(weacherStr);
		Weacher w = new Weacher();
		w.setDate(jsonObj.getString("date"));
		JSONArray results = jsonObj.getJSONArray("results");
		JSONObject result = results.getJSONObject(0);
		w.setCurrentCity(result.getString("currentCity"));
		w.setPm25(result.getString("pm25"));
		JSONArray index = result.getJSONArray("index");
		w.setCy_zs(index.getJSONObject(0).getString("zs"));
		w.setXc_zs(index.getJSONObject(1).getString("zs"));
		w.setGm_zs(index.getJSONObject(2).getString("zs"));
		w.setYd_zs(index.getJSONObject(3).getString("zs"));
		w.setZwx_zs(index.getJSONObject(4).getString("zs"));

		w.setCy_des(index.getJSONObject(0).getString("des"));
		w.setXc_des(index.getJSONObject(1).getString("des"));
		w.setGm_des(index.getJSONObject(2).getString("des"));
		w.setYd_des(index.getJSONObject(3).getString("des"));
		w.setZwx_des(index.getJSONObject(4).getString("des"));

		JSONArray weather_data = result.getJSONArray("weather_data");
		w.setDayPictureUrl(weather_data.getJSONObject(0).getString("dayPictureUrl"));
		w.setNigthPictureUrl(weather_data.getJSONObject(0).getString("nightPictureUrl"));
		w.setWeather(weather_data.getJSONObject(0).getString("weather"));
		w.setWind(weather_data.getJSONObject(0).getString("wind"));
		w.setTemperature(weather_data.getJSONObject(0).getString("temperature"));
		logger.info(w);
		return w;
	}
}
