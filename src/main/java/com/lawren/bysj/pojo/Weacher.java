package com.lawren.bysj.pojo;

public class Weacher 
{
	private String date;//日期yyyy-mm-dd
	private String currentCity;//城市
	private String pm25;//pm2.5
	private String dayPictureUrl;//白天天气图片
	private String nigthPictureUrl;//晚上天气图片
	private String weather;//天气文字
	private String wind;//风力风向
	private String temperature;//温度范围
	private String zwx_zs;//紫外线强度
	private String zwx_des;//紫外线强度指数
	private String cy_zs;//穿衣
	private String cy_des;//穿衣指数
	private String xc_zs;//洗车
	private String xc_des;//洗车指数
	private String gm_zs;//感冒
	private String gm_des;//感冒指数
	private String yd_zs;//运动
	private String yd_des;//运动指数
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	public String getDayPictureUrl() {
		return dayPictureUrl;
	}
	public void setDayPictureUrl(String dayPictureUrl) {
		this.dayPictureUrl = dayPictureUrl;
	}
	public String getNigthPictureUrl() {
		return nigthPictureUrl;
	}
	public void setNigthPictureUrl(String nigthPictureUrl) {
		this.nigthPictureUrl = nigthPictureUrl;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getZwx_zs() {
		return zwx_zs;
	}
	public void setZwx_zs(String zwx_zs) {
		this.zwx_zs = zwx_zs;
	}
	public String getZwx_des() {
		return zwx_des;
	}
	public void setZwx_des(String zwx_des) {
		this.zwx_des = zwx_des;
	}
	public String getCy_zs() {
		return cy_zs;
	}
	public void setCy_zs(String cy_zs) {
		this.cy_zs = cy_zs;
	}
	public String getCy_des() {
		return cy_des;
	}
	public void setCy_des(String cy_des) {
		this.cy_des = cy_des;
	}
	public String getXc_zs() {
		return xc_zs;
	}
	public void setXc_zs(String xc_zs) {
		this.xc_zs = xc_zs;
	}
	public String getXc_des() {
		return xc_des;
	}
	public void setXc_des(String xc_des) {
		this.xc_des = xc_des;
	}
	public String getGm_zs() {
		return gm_zs;
	}
	public void setGm_zs(String gm_zs) {
		this.gm_zs = gm_zs;
	}
	public String getGm_des() {
		return gm_des;
	}
	public void setGm_des(String gm_des) {
		this.gm_des = gm_des;
	}
	public String getYd_zs() {
		return yd_zs;
	}
	public void setYd_zs(String yd_zs) {
		this.yd_zs = yd_zs;
	}
	public String getYd_des() {
		return yd_des;
	}
	public void setYd_des(String yd_des) {
		this.yd_des = yd_des;
	}
	@Override
	public String toString() {
		return "Weacher [日期=" + date + ", 城市=" + currentCity + ", pm25=" + pm25 + ", 白天图片路径="
				+ dayPictureUrl + ", 晚上图片路径=" + nigthPictureUrl + ", 天气情况=" + weather + ", 风力风速=" + wind
				+ ", 温度范围=" + temperature + ", zwx_zs=" + zwx_zs + ", zwx_des=" + zwx_des + "]";
	}
	
	
	
}
