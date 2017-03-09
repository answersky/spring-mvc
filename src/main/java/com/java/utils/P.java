package com.java.utils;

import com.alibaba.druid.support.json.JSONUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.java.utils.Param.BAGS;
import static com.java.utils.Param.CATALOG;
import static com.java.utils.Param.CURRENCY;
import static com.java.utils.Param.DELIVERY;
import static com.java.utils.Param.DESCRIPTION;
import static com.java.utils.Param.DOCURL;
import static com.java.utils.Param.EMBGO;
import static com.java.utils.Param.GRABURL;
import static com.java.utils.Param.INVENTORY;
import static com.java.utils.Param.LEAD;
import static com.java.utils.Param.MFS;
import static com.java.utils.Param.MOQ;
import static com.java.utils.Param.PARAM;
import static com.java.utils.Param.PICURL;
import static com.java.utils.Param.PKG;
import static com.java.utils.Param.PN;
import static com.java.utils.Param.PRICE;
import static com.java.utils.Param.REGION;
import static com.java.utils.Param.ROHS;
import static com.java.utils.Param.SHOPID;
import static com.java.utils.Param.STORELIST;
import static com.java.utils.Param.SUPPLIERID;
import static com.java.utils.Param.SUPPLIERPN;
import static com.java.utils.Param.TAX;


public class P {



	private Map<String, Field> fieldMap = new HashMap<String, Field>();

	private List<S> stores = new ArrayList<S>();

	private Element doc;

	public P(Element document) {
		this.doc = document;
	}



	public Field price(String value) {
		return price(value, true);
	}

	public Field price(String value, boolean isPath) {
		return setField(PRICE, value, isPath);
	}

	public Field grabUrl(String value) {
		return grabUrl(value, true);
	}

	public Field grabUrl(String value, boolean isPath) {
		return setField(GRABURL, value, isPath);
	}

	public Field pn(String value)  {
		return pn(value, true);
	}

	public Field pn(String value, boolean isPath)  {
		Field field = setField(PN, value, isPath);
		if (field == null || field.getValue() == null
				|| "".equals(field.getValue().toString().trim())) {
			System.out.println("出错了");
		}
		return field;
	}

	public Field mfs(String value) {
		return mfs(value, true);
	}
	
	public Field mfs(String value, boolean isPath) {
		return setField(MFS, value, isPath);
	}	
	public Field supplierPn(String value) {
		return supplierPn(value, true);
	}
	public Field supplierPn(String value, boolean isPath) {
		return setField(SUPPLIERPN, value, isPath);
	}

	public Field supplierId(String value) {
		return supplierId(value, true);
	}

	public Field supplierId(String value, boolean isPath) {
		return setField(SUPPLIERID, value, isPath);
	}

	public Field shopId(String value) {
		return shopId(value, true);
	}

	public Field shopId(String value, boolean isPath) {
		return setField(SHOPID, value, isPath);
	}

	public Field pkg(String value) {
		return pkg(value, true);
	}

	public Field pkg(String value, boolean isPath) {
		return setField(PKG, value, isPath);
	}

	public Field bags(String value) {
		return bags(value, true);
	}

	public Field bags(String value, boolean isPath) {
		return setField(BAGS, value, isPath);
	}

	public Field rohs(String value) {
		return rohs(value, true);
	}

	public Field rohs(String value, boolean isPath) {
		return setField(ROHS, value, isPath);
	}

	public Field lead(String value) {
		return lead(value, true);
	}

	public Field lead(String value, boolean isPath) {
		return setField(LEAD, value, isPath);
	}

	public Field catalog(String value) {
		return catalog(value, true);
	}

	public Field catalog(String value, boolean isPath) {
		return setField(CATALOG, value, isPath);
	}

	public Field description(String value) {
		return description(value, true);
	}

	public Field description(String value, boolean isPath) {
		return setField(DESCRIPTION, value, isPath);
	}

	public void param(String rootPath, String keyPath, String valuePath) {
		param(rootPath, keyPath, valuePath, null);
	}
	public void param(String rootPath, String keyPath, String valuePath,
			Filter filter) {
		Elements element = HtmlParse.getElement(doc, rootPath);
		Map<String, String> re = new LinkedHashMap<String, String>();
		for (Element e : element) {
			String k = e.select(keyPath).text();
			String v = e.select(valuePath).text();
			if (filter != null) {
				k = filter.filter(k);
				v = filter.filter(v);
			}
			if (k != null || v != null) {
				re.put(k, v);
			}
		}
		setField(PARAM, JSONUtils.toJSONString(re), false);
	}

	public void param(LinkedHashMap<String, String> paramMap) {
		setField(PARAM, JSONUtils.toJSONString(paramMap), false);
	}

	public Field embgo(String value) {
		return embgo(value, true);
	}

	public Field embgo(String value, boolean isPath) {
		return setField(EMBGO, value, isPath);
	}

	public Field picUrl(String value) {
		return picUrl(value, true);
	}

	public Field picUrl(String value, boolean isPath) {
		return setField(PICURL, value, isPath);
	}

	public Field docUrl(String value) {
		return docUrl(value, true);
	}

	public Field docUrl(String path, boolean isPath) {
		return setField(DOCURL, path, isPath);
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

	public Field moq(String value) {
		return moq(value, true);
	}

	public Field moq(String value, boolean isPath) {
		return setField(MOQ, value, isPath);
	}

	public Field region(String value) {
		return region(value, true);
	}

	public Field region(String value, boolean isPath) {
		return setField(REGION, value, isPath);
	}

	public Field delivery(String value) {
		return delivery(value, true);
	}

	public Field delivery(String value, boolean isPath) {
		return setField(DELIVERY, value, isPath);
	}

	public Field currency(String value) {
		return currency(value, true);
	}

	public Field currency(String value, boolean isPath) {
		return setField(CURRENCY, value, isPath);
	}

	public Field officalPrice(String value) {
		return officalPrice(value, true);
	}

	public Field officalPrice(String value, boolean isPath) {
		return setField(PRICE, value, isPath);
	}

	public void addStore(S s) {
		stores.add(s);
	}

	public Field storeList(String value) {
		return storeList(value, true);
	}

	public Field storeList(String value, boolean isPath) {
		return setField(STORELIST, value, isPath);
	}

	private Field setField(String fieldName, String value, boolean isPath) {
		Field field = null;
        //对&nbsp;处理
        if (value.contains(" ")){
            value=value.replace(" ","");
        }
		if (isPath) {
			field = getFieldByPath(fieldName, value);
		} else {
			field = new StringField(fieldName, value);
		}
		if (field != null) {
			fieldMap.put(fieldName, field);
		}

		return field;
	}

	private Field getFieldByPath(String fieldName, String path) {
		Elements value = HtmlParse.getElement(doc, path);
		if (value == null) {
			return null;
		}
		return new ElementField(fieldName, value);
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<String> set = fieldMap.keySet();
		Iterator<String> ite = set.iterator();
		while (ite.hasNext()) {
			String key = ite.next();
			Field value = fieldMap.get(key);
			map.put(key, value.getValue());
		}

		List<Map> storeMaps = new ArrayList<Map>();
		for (S s : stores) {
			storeMaps.add(s.toMap());
		}
		map.put(STORELIST, storeMaps);
		// map.put(P_VERSION, VERSION_NUM);
		return map;

	}

	public String toString() {
		return fieldMap.toString();
	}

	public void param(String string, Object object) {
		// TODO Auto-generated method stub
		
	}

}
