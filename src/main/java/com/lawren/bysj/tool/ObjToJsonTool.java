package com.lawren.bysj.tool;

import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ObjToJsonTool {

	/**
	 * 将分析得出的数据转化成图表能够识别的JSON类型的数据类型
	 * @param map
	 * @return
	 */
	public static JSONObject objToChart2DData(Map<String, Map<String, Integer>> map){
		/***************创建categories数组******************/
		JSONArray categories=new JSONArray();
		JSONObject object=new JSONObject();
		JSONArray category=new JSONArray();
		for(int i=1;i<13;i++){
			JSONObject obj=new JSONObject();
			obj.put("label", getMonth(i));
			category.add(obj);
		}
		object.put("category", category);
		categories.add(object);
		/*****************创建dataset数组******************/
		JSONArray dataset=new JSONArray();
		Set<String> yearSet=map.keySet();
		int index=0;
		for(String year:yearSet){
			JSONObject obj=new JSONObject();
			obj.put("seriesname", year);
			obj.put("allowDrag", index++ );
			Map<String, Integer> yeardata=map.get(year);
			JSONArray data=new JSONArray();
			for(Integer i=1;i<13;i++){
				JSONObject valObj=new JSONObject();
				valObj.put("value", yeardata.get(i.toString()));
				data.add(valObj);
			}
			obj.put("data", data);
			dataset.add(obj);
		}
		/*******************构建最终对象********************/
		JSONObject chartData=new JSONObject();
		chartData.put("categories", categories);
		chartData.put("dataset", dataset);
		return chartData;
	}

	public static String getMonth(int month) {
		String monthStr="";
		switch (month) {
		case 1:
			monthStr="Jan";
			break;
		case 2:
			monthStr="Feb";
			break;
		case 3:
			monthStr="Mar";
			break;
		case 4:
			monthStr="Apr";
			break;
		case 5:
			monthStr="May";
			break;
		case 6:
			monthStr="Jun";
			break;
		case 7:
			monthStr="Jul";
			break;
		case 8:
			monthStr="Aug";
			break;
		case 9:
			monthStr="Sep";
			break;
		case 10:
			monthStr="Oct";
			break;
		case 11:
			monthStr="Nov";
			break;
		case 12:
			monthStr="Dec";
			break;

		default:
			break;
		}
		return monthStr;
	}
}
