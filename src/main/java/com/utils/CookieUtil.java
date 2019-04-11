package com.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    private static Cookie cookie;

    public static Cookie getInstance(String name,String value){
        cookie = new Cookie(name,value);
        cookie.setPath("/");
        return cookie;
    }

    public static Cookie getCookie(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(name.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(name.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, Cookie cookie){
        response.addCookie(cookie);
    }

    public static void delCookie(HttpServletRequest request,HttpServletResponse response,String name){     //页面js操作
        Cookie cookie = getCookie(request,name);
        cookie.setMaxAge(0);
    }
}
