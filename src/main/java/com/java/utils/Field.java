package com.java.utils;

public interface Field {
	
	Field attr(String attr);
	
	Field eq(int index);

	Field del(String str);

	Field trim();

	Field toUpperCase() ;

	Field replace(String target, String replacement);
	
	Field filter(Filter filter);

	String getName();
	
	Object getValue();
	
	

}
