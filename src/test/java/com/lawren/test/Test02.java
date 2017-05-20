package com.lawren.test;

import java.util.HashSet;
import java.util.Set;

public class Test02 {
	public static void main(String[] args) {
		Set<String> set=new HashSet<>();
		set.add("aas");
		set.add("aas");
		set.add("aaxssss");
		set.add("aaxssss");
		set.add("aas");
		System.out.println(set.size());
	}
}
