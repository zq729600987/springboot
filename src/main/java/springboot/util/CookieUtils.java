package springboot.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

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

    public static void setCookie(HttpServletResponse response, Cookie cookie){
        response.addCookie(cookie);
    }

    public static void delCookie(HttpServletResponse response,String name){     //页面js操作
        Cookie cookie = getInstance(name,null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
