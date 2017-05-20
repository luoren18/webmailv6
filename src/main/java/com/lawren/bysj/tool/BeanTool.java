package com.lawren.bysj.tool;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.lawren.bysj.pojo.EmailView;
@Component("beanTool")
public class BeanTool {

	private Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取属性名数组
	 */
	private String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getType());
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 */
	public <T> List<EmailView> getFiledsInfo(T t) {
		Field[] fields = t.getClass().getDeclaredFields();
		List<EmailView> list = new ArrayList<>();
		for (int i = 0; i < fields.length; i++) {
			EmailView view = new EmailView();
			view.setLabel(fields[i].getName());
			view.setValue(getFieldValueByName(fields[i].getName(), t));
			list.add(view);
		}
		return list;
	}

	/**
	 * 获取对象的所有属性值，返回一个对象数组
	 */
	public Object[] getFiledValues(Object o) {
		String[] fieldNames = this.getFiledName(o);
		Object[] value = new Object[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			value[i] = this.getFieldValueByName(fieldNames[i], o);
		}
		return value;
	}
	
	public Map<String, Integer> initMap(){
		Map<String, Integer> map=new HashMap<>();
		for(Integer i=1;i<13;i++){
			map.put(i.toString(), 0);
		}
		return map;
	} 
	
	
}
