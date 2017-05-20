function loadWeatherInfo() {
	$.get("getweather", function(data) {
		writeWeatherInfo(data);
	});
}

function writeWeatherInfo(data){
	$("#currentCity").text(data.currentCity);
	var date=new Date();
	var hours=date.getHours();
	if(hours>=18||hours<5){
		$("#pictureUrl").attr("src",data.nigthPictureUrl);
	}else{
		$("#pictureUrl").attr("src",data.dayPictureUrl);
	}
	$("#weather").text(data.weather);
	$("#temperature").text(data.temperature);
	$("#wind").text(data.wind);
}