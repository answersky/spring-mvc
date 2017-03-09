package com.java.utils;

import org.jsoup.select.Elements;

public class ElementField implements Field {

	private String fieldName;
	private Elements element;
	private String value;

	public ElementField(String fieldName, Elements element) {
		if(element==null){
			throw new IllegalArgumentException("element is null");
		}
		this.fieldName = fieldName;
		this.element = element;
	}

	public Field attr(String attr) {
		String re = null;
		for (int i = 0; i < element.size(); i++) {
			if (i == 0) {
				re = element.get(i).attr(attr);
			} else {
				re = re + "," + element.get(i).attr(attr);
			}
		}
		this.value = re;
		return this;
	}

	public Field eq(int index) {
		if (element.size() >= index) {
			this.element = element.eq(index);
		}
		return this;
	}

	public Field del(String str) {
		value = getValueStr(value).replace(str, "");
		return this;
	}

	public Field trim() {
		value = getValueStr(value).trim();
		return this;
	}

	public Field toUpperCase() {
		value = getValueStr(value).toUpperCase();
		return this;
	}

	public Field replace(String target, String replacement) {
		value = getValueStr(value);
		if(value!=null){
			value=value.replace(target, replacement);
		}
		return this;
	}
	
	public Field filter(Filter filter){
		value = getValueStr(value);
		value=filter.filter(value);
		return this;
	}

	private String getValueStr(Object value) {
		if (value == null) {
			return element.text();
		}
		return (String) value;

	}

	public String toString() {
		if (value == null) {
			value = element.text();
		}
		return value;
	}

	public String getName() {
		return fieldName;
	}

	public Object getValue() {
		if (value == null) {
			value =element.text();
			if(value != null) {
				value = value.trim();
			}
		}

		return value;

	}

}
