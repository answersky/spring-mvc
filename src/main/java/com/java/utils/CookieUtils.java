package com.java.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/8/17.
 */
public class CookieUtils {

    public static String getCookie(HttpServletRequest request,String name){
        Cookie[] cookies=request.getCookies();
        String cookie="";
        for(int i=0;i<cookies.length;i++){
            if(name.equals(cookies[i].getName())){
                cookie=cookies[i].getValue();
                break;
            }
        }

        return cookie;
    }

}
