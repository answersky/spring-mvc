package com.java.utils;

public class StringField implements Field{
	
	private String fieldName;
	private String value;
	
	public StringField(String fieldName,String value){
		this.fieldName=fieldName;
		this.value=value;
	}
	public Field del(String str) {
		value=value.replaceAll(str, "");
		return this;
	}

	public Field trim() {
		value=value.trim();
		return this;
	}

	public Field toUpperCase() {
		value=value.toUpperCase();			
		return this;
	}

		
	
	public Field replace(String target, String replacement) {
		if(value!=null){
			value=value.replace(target, replacement);
		}
		return this;
	}
	
	
	public Field filter(Filter filter){
		value=filter.filter(value);
		return this;
	}

	public String toString() {		
		return value;
	}

	public String getName() {
		return fieldName;
	}
	
	public Object getValue(){
		if(value != null) {
			value = value.trim();
		}
		return value;
	}
	
    public Field attr(String attr){
    	throw new RuntimeException("method not supported!");
    }
	
	public Field eq(int index){
		throw new RuntimeException("method not supported!");
	}
	
}
