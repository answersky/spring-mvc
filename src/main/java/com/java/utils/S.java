package com.java.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.java.utils.Param.*;

public class S {

	private Map<String, Field> fieldMap = new HashMap<String, Field>();
	private Element doc;

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<String> set = fieldMap.keySet();
		Iterator<String> ite = set.iterator();
		while (ite.hasNext()) {
			String key = ite.next();
			Field value = fieldMap.get(key);
			map.put(key, value.getValue());
		}
		return map;
	}

	public S(Element document) {
		this.doc = document;
	}

	public Field region(String value) {
		return region(value, true);
	}

	public Field region(String value, boolean isPath) {
		return setField(REGION, value, isPath);
	}

	public void price(List value) {
		setField(PRICE, JSONUtils.toJSONString(value), false);
	}

	public void attachStore() {

	}

	public void price(String path, String amount, String price, Filter filter) {
		Elements element = HtmlParse.getElement(doc, path);

		List<Map> relist = new ArrayList<Map>();
		int i = 0;
		for (Element e : element) {
			Map<String, String> re = new HashMap<String, String>();
			String k = e.select(amount).text().trim();
			String v = e.select(price).text().trim();

			if (filter != null) {
				k = filter.filter(k);
				v = filter.filter(v);
			}
			k = k.replaceAll("[^0-9.]", "");
			v = v.replaceAll("[^0-9.]", "");
			try {
				// 检验 k v 是否是数字，不是数字跳过后面的所有价格区间
				Integer.valueOf(k);
				Double.valueOf(v);
				Field field = (StringField) fieldMap.get(MOQ);
				// 这里防止覆盖起订量。这里设置了判断
				if (i == 0) {
					if (field == null
							|| StringUtils.isEmpty((String) field.getValue())) {
						moq(k, false);
					}
				}
				i++;
				re.put(AMOUNT, k);
				re.put(PRICESTR, v);
				relist.add(re);
			} catch (Exception e1) {
				continue;
			}

		}
		setField(PRICE, JSONUtils.toJSONString(relist), false);
	}

	public void price(String path) {
		setField(PRICE, path, true);
	}

	private Field setField(String fieldName, String value, boolean isPath) {
		Field field = null;
		if (isPath) {
			field = getFieldByPath(fieldName, value);
		} else {
			field = new StringField(fieldName, value);
		}
		fieldMap.put(fieldName, field);
		return field;
	}

	public Field tax(String value) {
		return tax(value, true);
	}

	public Field tax(String value, boolean isPath) {
		return setField(TAX, value, isPath);
	}

	public Field inventory(String value) {

		return inventory(value, true);
	}

	public Field inventory(String value, boolean isPath) {
		return setField(INVENTORY, value, isPath);
	}

	/**
	 * 处理价格的时候会将第一个起订量设置为moq，如果需要额外处理，需要在price操作之后再进行moq操作
	 * 
	 * @param value
	 * @return
	 */
	public Field moq(String value) {
		return moq(value, true);
	}

	/**
	 * 处理价格的时候会将第一个起订量设置为moq，如果需要额外处理，需要在price操作之后再进行moq操作
	 * 
	 * @param value
	 * @return
	 */
	public Field moq(String value, boolean isPath) {
		return setField(MOQ, value, isPath);
	}

	/**
	 * 可以为空
	 * 
	 * @param value
	 * @return
	 */
	public Field delivery(String value) {
		return delivery(value, true);
	}

	/**
	 * 可以为空
	 * 
	 * @param value
	 * @return
	 */
	public Field delivery(String value, boolean isPath) {
		return setField(DELIVERY, value, isPath);
	}

	public Field currency(String value) {
		return currency(value, true);
	}

	public Field currency(String value, boolean isPath) {
		return setField(CURRENCY, value, isPath);
	}

	/**
	 * 可以为空
	 * 
	 * @param value
	 * @return
	 */

	public Field officalPrice(String value) {
		return officalPrice(value, true);
	}

	/**
	 * 可以为空
	 * 
	 * @param value
	 * @return
	 */
	public Field officalPrice(String value, boolean isPath) {
		return setField(PRICE, value, isPath);
	}

	public Field attachStore(String value) {
		return attachStore(value, true);
	}

	public Field attachStore(String value, boolean isPath) {
		return setField(STOREATTACH, value, isPath);
	}

	private Field getFieldByPath(String fieldName, Object path) {
		Elements value = HtmlParse.getElement(doc, path.toString());
		return new ElementField(fieldName, value);
	}

	public Field storeList(String value) {
		return storeList(value, true);
	}

	public Field storeList(String value, boolean isPath) {
		return setField(STORELIST, value, isPath);
	}

	public static void main(String[] args) {
		String k = "2,500.12";
		String s = "";

//		boolean a = StringUtils.isNumeric(s);
//		System.out.println(a);
		System.out.println(k.replace(",", "").replace(".", ""));

	}

}
