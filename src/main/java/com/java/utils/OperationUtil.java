package com.java.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.java.domain.OfficePriceTag;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuf on 2016/8/25.
 */
public class OperationUtil {
    /**
     * 转大写操作
     * @param str
     * @return
     */
    private static String upperCase(String str){
        return str.toUpperCase();
    }

    /**
     * 获取第一个元素
     * @param elements
     * @return
     */
    private static Element last(Elements elements){
        return elements.last();
    }

    /**
     * 获取第一个元素
     * @param elements
     * @return
     */
    private static Element first(Elements elements){
        return elements.first();
    }

    /**
     * 获取文本
     * @param element
     * @return
     */
    private static String text(Element element){
        return element.text();
    }

    /**
     * 去掉前后空格
     * @param str
     * @return
     */
    private static String trim(String str){
        return str.trim();
    }

    /**
     * 判断字符串是否包含另外一个字符串
     * @param str
     * @param contains
     * @return
     */
    private static boolean contains(String str,String contains){
        return str.contains(contains);
    }

    /**
     * 替换
     * @param arr
     * @param str
     * @return
     */
    private static String replace(String[] arr,String str){
        if(arr.length==2){
            return str.replace(arr[1],"");
        }
        else if(arr.length==3){
            return str.replace(arr[1],arr[2]);
        }
        return null;
    }

    /**
     * 替换掉所有非数字的
     * @param str
     * @return
     */
    private static String replaceAll(String str){
        return str.replaceAll("[^0-9.]","");
    }


    public static String operation(Elements elements, List<String> operations){
        String str=null;
        Element element=null;
        //没有任何操作，当做直接抓到元素值
        if(elements==null){
            return null;
        }

        if(operations.size()<1){
            str=elements.text().toString();
            return str;
        }
        for(String string:operations){
            if(Constant.UpperCase.equals(string)){
                str=upperCase(str);
            }

            if(Constant.First.equals(string)){
                element=first(elements);
            }

            if(Constant.Last.equals(string)){
                element=first(elements);
            }

            if(Constant.Text.equals(string)){
                if(element==null){
                    str=elements.text();
                }else {
                    str=text(element);
                }
            }

            if(Constant.Trim.equals(string)){
                str=trim(str);
            }

            if(string.contains(Constant.Replace+"=>")){
                String[] arr=string.split("=>");
                System.out.println(arr.length);
               str=replace(arr,str);
            }

            if(Constant.ReplaceAll.equals(string)){
               str=replaceAll(str);
            }

            if(Constant.Split.equals(string)){
                String[] arr=string.split("=>");
                //拆分的条件
                String splitCondition=arr[1];
                //取值的范围
                int index=Integer.parseInt(arr[2]);
                str=str.split(splitCondition)[index];
            }
        }

        return str;
    }

    /**
     * 处理阶梯价
     * @param elements
     * @param operations
     * @return
     */
    public static  List<Map<String, Object>> amountAndPriceOperation(Elements elements, List<String> operations, LinkedHashMap<String, List<String>> amountMap,LinkedHashMap<String, List<String>> priceMap){
        List<Map<String, Object>> prilist = new ArrayList<Map<String, Object>>();

        //没有任何操作，当做直接抓到元素值
        if(elements==null){
            return null;
        }

        for(String string:operations){
            if(Constant.For.equals(string)){
                for (Element element : elements) {
                    Map<String, Object> primap = new LinkedHashMap<String, Object>();

                    //一般情况amountMap和priceMap里的key和value只存在一对
                    //amount
                    amountMap.forEach((key,value)->{
                        Elements ele=element.select(key);
                        String amount=operation(ele,value);
                        primap.put("amount", amount);
                    });

                    //price
                    priceMap.forEach((key,value)->{
                        Elements ele=element.select(key);
                        String price=operation(ele,value);
                        primap.put("priceStr", price);
                    });

                    prilist.add(primap);
                }
            }
        }

        return prilist;
    }
}
