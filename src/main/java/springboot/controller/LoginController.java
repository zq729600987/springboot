package springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springboot.service.UserService;
import springboot.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("login");
    }

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String token = userService.userLogin(username,password);

        Cookie cookie = CookieUtils.getInstance("user_token",token);
        CookieUtils.setCookie(response,cookie);
        Cookie[] cookies = request.getCookies();
        return null;
    }

}
