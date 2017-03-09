package com.java.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class HtmlParse {
	
	@SuppressWarnings({ "unused", "rawtypes" })
	public static List readAllForElements(Element e, String elementsPath, String messagePath, String message) {
		if (e == null) {
			return null;
		}
		try {
			Elements es= e.select(elementsPath);
			if(es!=null&&es.size()>0){
				//
				List re =new ArrayList();
				for(Element _e:es){
					if(_e!=null){
						Elements _es=_e.select(messagePath) ;
						if(_es!=null&&_es.size()>0&&_es.first()!=null){ 
							Element __e=_es.first();
							String url=__e.attr(message);
							re.add(url);
						}
					}
				}
				return re;
			}else{
				//没有找到对象
				return null;
			}
			
		} catch (Exception e1) {
			return null;
		}  
	}
	
	
	
	public static Document getDoc(String url) {
		try {
			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				System.out.println(url);
				e.printStackTrace();
			}
			return doc;
		} catch (Exception e1) {
			return null;
		}
	}

	public static Document getDocFromString(String html) {
		try {
			Document doc = null;
			doc = Jsoup.parse(html);
			return doc;
		} catch (Exception e1) {
			return null;
		}
	}

	public static Elements getElement(Element e, String path) {
		if (e == null) {
			return null;
		}
		try {
			return e.select(path);
		} catch (Exception e1) {
			return null;
		}

	}

	public static String getAttrBySelect(Element doc, String path, String attr) {
		try {
			if (doc == null) {
				return null;
			} else {
				Elements elements = doc.select(path);
				if (elements != null && elements.size() >= 1) {
					Element re = elements.first();
					if (re != null) {
						return re.attr(attr);
					} else {
						return null;
					}
				} else {
					return null;
				}
			}
		} catch (Exception i) {
			return null;
		}
	}

	/**
	 * 判断一个path存不存在
	 * 
	 * @param doc
	 * @param path
	 * @return
	 */
	public static boolean isHave(Element doc, String path) {
		try {
			if (doc == null) {
				return false;
			} else {
				Elements elements = doc.select(path);
				if (elements != null && elements.size() > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception i) {
			return false;
		}

	}

	public static String getTextById(Element doc, String id) {
		try {
			Element re = doc.getElementById(id);
			if (re != null) {
				return re.text();
			} else {
				return null;
			}
		} catch (Exception i) {
			return null;
		}
	}

	public static String getTextBySelect(Element doc, String path) {
		return getTextBySelect(doc, path, 0);
	}

	public static String getTextBySelect(Element doc, String path, int index) {
		try {
			if (doc == null) {
				return null;
			} else {
				Elements elements = doc.select(path);
				if (elements != null && elements.size() >= index) {
					Element re = elements.get(index);
					if (re != null) {
						return re.text();
					} else {
						return null;
					}
				} else {
					return null;
				}
			}

		} catch (Exception i) {
			return null;
		}
	}

	/**
	 * 通过classname 获取第index个元素的text内容
	 * 
	 * @param classname
	 * @param index
	 * @param doc
	 * @return
	 */
	public static String getTextByClass(String classname, int index, Element doc) {
		try {
			if (doc == null) {
				return null;
			} else {
				Elements elements = doc.getElementsByClass(classname);
				if (elements != null && elements.size() >= index) {
					Element re = elements.get(index);
					if (re != null) {
						return re.text();
					} else {
						return null;
					}
				} else {
					return null;
				}
			}
		} catch (Exception i) {
			return null;
		}
	}



}
