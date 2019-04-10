package com.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.pojo.User;
import com.springboot.service.UserService;
import com.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("login");
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Map doLogin(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        Cookie cookie = CookieUtils.getCookie(request,"user_token");
        if(cookie != null){
            User user = (User) redisTemplate.opsForValue().get("user_" + cookie.getValue());
            if(null != user){
                modelMap.put("returncode","0");
                modelMap.put("returnmsg","登录成功");
                return modelMap;
            }
        }

        String userno = request.getParameter("userno");
        String password = request.getParameter("password");

        /*Assert.hasLength(userno,"用户名不能为空");
        Assert.hasLength(password,"密码不能为空");*/

        String token = userService.userLogin(userno,password);

        cookie = CookieUtils.getInstance("user_token",token);
        CookieUtils.setCookie(response,cookie);

        modelMap.put("returncode","0");
        modelMap.put("returnmsg","登录成功");
        return modelMap;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("login");
    }
}
