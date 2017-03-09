package com.java.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * Created by liuf on 2016/9/2.
 */
public class GoogleTranslate {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
       /* String trans = null;
        Translate.setHttpReferrer("http://translate.google.cn");
        try {
            trans = Translate.translate("你好", Language.CHINESE, Language.ENGLISH);
            //		汉译英
//			trans = Translate.translate("hello", Language.ENGLISH, Language.CHINESE);
            System.out.println(trans);
//             英译汉
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        String text="MARKING INFORMATION  TAPING SPECIFICATIONS";
    translate(text);
    }

    //有道翻译
    public static String translate(String text){
//        String text="hello";
        try {
            Document doc= Jsoup.connect("http://fanyi.youdao.com/openapi.do?keyfrom=pdf-translation&key=2136595030&type=data&doctype=json&version=1.1&q="+text).ignoreContentType(true).get();
            String json=doc.text();
            Map map= (Map) JSONUtils.parse(json);
            String translateText=map.get("translation").toString();
            System.out.println(translateText);
            return translateText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
