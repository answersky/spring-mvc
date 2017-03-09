package com.java.utils;

/**
 * Created by liuf on 2016/10/23.
 */

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.java.domain.Fruit;
import org.apache.poi.ss.formula.functions.T;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *使用google的Gson工具类进行封装
 */
public class JsonUtils {

    /**
     * 对象转json
     * @param object
     * @return
     */
    public static String objectToJson(Object object){
        Gson gson=new Gson();
        return gson.toJson(object);
    }

    /**
     * 将json转成list<Object>
     * @param json
     * @return
     */
    public static List<Object> listFromJson(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<Object>>() {}.getType();
        return gson.fromJson(json,type);
    }

    /**
     * json转map
     * @param jsonStr
     * @return
     */
    public static Map mapFormJSONStr(String jsonStr) {
        try {
            ObjectMapper e = new ObjectMapper();
            return (Map)e.readValue(jsonStr, Map.class);
        } catch (Exception var2) {
            throw new JSONException("build object from " + jsonStr + " fail", var2);
        }
    }

    /**
     * json转list
     * @param jsonStr
     * @return
     */
    public static List listFormJSONStr(String jsonStr) {
        try {
            ObjectMapper e = new ObjectMapper();
            return (List)e.readValue(jsonStr, List.class);
        } catch (Exception var2) {
            throw new JSONException("build object from " + jsonStr + " fail", var2);
        }
    }

    public static void main(String[] args) {
        List<Fruit> list=new ArrayList<>();
        Fruit fruit=new Fruit();
        fruit.setName("apple");
        fruit.setColor("red");
        fruit.setWeight("12");
        list.add(fruit);
        System.out.println("对象转json===============");
        String json=objectToJson(list);
        System.out.println(json);

        System.out.println("json转对象===============");
        List<Object> objList=listFromJson(json);
        System.out.println(objList);

        System.out.println("json转map对象===============");
        Map<String,String> map=new LinkedHashMap<>();
        map.put("1","zhangsan");
        map.put("2","wanwu");
        map.put("3","zhangliu");
        String mapJson=objectToJson(map);
        System.out.println(mapJson);
        Map<String,String> mapData=mapFormJSONStr(mapJson);
        System.out.println(mapData);
    }
}
