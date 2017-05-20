<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8" />
<link rel="icon" href="images/email_icon.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/email_icon.ico"
	type="image/x-icon">
<title>My first chart using FusionCharts Suite XT</title>
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/fusioncharts.js"
	type="text/javascript" charset="utf-8"></script>
<script src="js/fusioncharts.charts.js" type="text/javascript"
	charset="utf-8"></script>
<script src="js/themes/fusioncharts.theme.fint.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	$(function() {
		$.post("getMailView", function(data) {
			/***************柱状折线图*******************/
			var revenueChart = new FusionCharts({
				"type" : "pareto3d",
				"renderAt" : "mailcount",
				"width" : "800",
				"height" : "500",
				"baseFontSize":"50",
				"palette" : "5",
				"dataFormat" : "json",
				"dataSource" : {
					"chart" : {
						"caption" : "邮件类型统计",
						"subCaption" : "---来自于开开邮箱数据",
						"xAxisName" : "邮件类型",
						"yAxisName" : "数量",
						"theme" : "zune,ocean"
					},
					"data" : data
				}
			});
			revenueChart.render();
			
			
			
			/**********************饼状图**********************/
			var revenueChart2 = new FusionCharts({
				"type" : "pie2d",
				"renderAt" : "mailcount2",
				"width" : "800",
				"height" : "500",
				"baseFontSize":"50",
				"palette" : "5",
				"dataFormat" : "json",
				"dataSource" : {
					"chart": {
				        "caption": "各类邮件占比统计",
				        "subCaption": "---来自于开开邮箱数据",
				        "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000,#ff3390,#88eecc,#bb8ff8",
				        "bgColor": "#ffffff",
				        "showBorder": "0",
				        "use3DLighting": "0",
				        "showShadow": "0",
				        "enableSmartLabels": "0",
				        "startingAngle": "0",
				        "showPercentValues": "1",
				        "showPercentInTooltip": "0",
				        "decimals": "1",
				        "captionFontSize": "14",
				        "subcaptionFontSize": "14",
				        "subcaptionFontBold": "0",
				        "toolTipColor": "#ffffff",
				        "toolTipBorderThickness": "0",
				        "toolTipBgColor": "#000000",
				        "toolTipBgAlpha": "80",
				        "toolTipBorderRadius": "2",
				        "toolTipPadding": "5",
				        "showHoverEffect": "1",
				        "showLegend": "1",
				        "legendBgColor": "#ffffff",
				        "legendBorderAlpha": "0",
				        "legendShadow": "0",
				        "legendItemFontSize": "10",
				        "legendItemFontColor": "#666666",
				        "useDataPlotColorForLabels": "1"
				    },
					"data" : data
				}
			});
			revenueChart2.render();
			/*****************不晓得如何描述的一个图*****************/
			var revenueChart3 = new FusionCharts({
				"type" : "funnel",
				"renderAt" : "mailcount3",
				"width" : "800",
				"height" : "400",
				"baseFontSize":"50",
				"palette" : "5",
				"dataFormat" : "json",
				"dataSource" : {
					"chart" : {
						"caption": "各类邮件所占比例",
				        "subcaption": "---来自于开开邮箱数据 ",
				        "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000,#ff3390,#88eecc,#bb8ff8",
				        "bgColor": "#ffffff",
				        "decimals": "1",
				        "showBorder": "0",
				        "isHollow": "1",
				        "labelDistance": "15",
				        "is2D": "1",
				        "plotTooltext": "所占比 : $percentOfPrevValue",
				        "showPercentValues": "1"
					},
					"data" : data
				}
			});
			revenueChart3.render();
			
		}, "json");
		
		
		
		
		
		/******************每年每月数据统计图******************/
		$.post("countEmailByDate",function(data){
			var revenueChart = new FusionCharts({
				"type" : "dragcolumn2d",
				"renderAt" : "countEmailByDate",
				"width" : "800",
				"height" : "500",
				"baseFontSize":"50",
				"palette" : "5",
				"dataFormat" : "json",
				"dataSource" : {
					"chart": {
				        "caption": "按年每月邮件数量统计",
				        "subCaption": "---来自于开开邮箱数据 ",
				        "xAxisName": "月份",
				        "yAxisName": "邮件数量",
				        "paletteColors": "#0075c2,#1aaf5d,#f2c500",
				        "bgColor": "#ffffff",
				        "showAlternateHGridColor": "0",
				        "showBorder": "0",
				        "showCanvasBorder": "0",
				        "baseFontColor": "#333333",
				        "baseFont": "Helvetica Neue,Arial",
				        "captionFontSize": "14",
				        "subcaptionFontSize": "14",
				        "subcaptionFontBold": "0",
				        "usePlotGradientColor": "0",
				        "toolTipColor": "#ffffff",
				        "toolTipBorderThickness": "0",
				        "toolTipBgColor": "#000000",
				        "toolTipBgAlpha": "80",
				        "toolTipBorderRadius": "2",
				        "toolTipPadding": "5",
				        "legendBgAlpha": "0",
				        "legendBorderAlpha": "0",
				        "legendShadow": "0",
				        "legendItemFontSize": "10",
				        "legendItemFontColor": "#666666",
				        "legendCaptionFontSize": "9",
				        "divlineAlpha": "100",
				        "divlineColor": "#999999",
				        "divlineThickness": "1",
				        "divLineDashed": "1",
				        "divLineDashLen": "1",
				        "theme":"zune,ocean"
				    },
				    "categories":data.categories,
				    "dataset":data.dataset
				}
			});
			revenueChart.render();
			
			
			
			var revenueChart2 = new FusionCharts({
				"type" : "inversemsline",
				"renderAt" : "countEmailByDate2",
				"width" : "800",
				"height" : "500",
				"baseFontSize":"50",
				"palette" : "5",
				"dataFormat" : "json",
				"dataSource" : {
					"chart": {
				        "caption": "每月邮件数量浮动比",
				        "subCaption": "---来自于开开邮箱数据",
				        "xAxisName": "月份",
				        "yAxisName": "浮动比",
				        "numberSuffix": "%",
				        "showBorder": "0",
				        "paletteColors": "#0075c2,#1aaf5d,#f2c500",
				        "bgColor": "#ffffff",
				        "usePlotGradientColor": "0",
				        "plotFillAlpha": "50",
				        "showCanvasBorder": "0",
				        "LegendShadow": "0",
				        "legendBorderAlpha": "0",
				        "showXAxisLine": "1",
				        "axisLineAlpha": "40",
				        "divlineColor": "#999999",
				        "divlineThickness": "1",
				        "divLineDashed": "1",
				        "divLineDashLen": "1",
				        "showAlternateHgridColor": "0",
				        "showValues": "0",
				        "baseFontColor": "#333333",
				        "baseFont": "Helvetica Neue,Arial",
				        "captionFontSize": "14",
				        "subcaptionFontSize": "14",
				        "subcaptionFontBold": "0",
				        "toolTipColor": "#ffffff",
				        "toolTipBorderThickness": "0",
				        "toolTipBgColor": "#000000",
				        "toolTipBgAlpha": "80",
				        "toolTipBorderRadius": "2",
				        "toolTipPadding": "5"
				    },
				    "categories":data.categories,
				    "dataset":data.dataset
				}
			});
			revenueChart2.render();
		},"json");
	});
</script>
</head>
<body>
	<div id="mailcount" style="padding-bottom :40px">各类类型邮件的数量</div>
	<div id="mailcount2" style="padding-bottom :40px">各类类型邮件的数量</div>
	<div id="mailcount3" style="padding-bottom :40px">各类类型邮件的数量</div>
	<div id="countEmailByDate" style="padding-bottom :40px">每年个月邮件数量</div>
	<div id="countEmailByDate2" style="padding-bottom :40px">每年个月邮件数量</div>
	
</body>
</html>