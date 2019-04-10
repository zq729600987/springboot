package com.springboot.controller;

import com.springboot.pojo.User;
import com.springboot.service.UserService;
import com.utils.CookieUtil;
import com.utils.DynamicBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public Map doLogin(HttpServletRequest request, HttpServletResponse response){
        DynamicBean back = new DynamicBean();

        Cookie cookie = CookieUtil.getCookie(request,"user_token");
        if(cookie != null){
            User user = (User) redisTemplate.opsForValue().get("user_" + cookie.getValue());
            if(null != user){
                back.put("returncode","success");
                back.put("returnmsg","登录成功");
                return back;
            }
        }

        String userno = request.getParameter("userno");
        String password = request.getParameter("password");

        DynamicBean bean = userService.userLogin(userno,password);
        if("0".equals(bean.getString("returncode"))){
            cookie = CookieUtil.getInstance("user_token",bean.getString("token"));
            CookieUtil.setCookie(response,cookie);

            back.put("returncode","0");
            back.put("returnmsg","登录成功");
        }else{
            back = bean;
        }
        return back;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("login");
    }
}
