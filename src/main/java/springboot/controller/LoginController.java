package springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springboot.mapper.UserMapper;
import springboot.pojo.User;
import springboot.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("login");
    }

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUser(username);
        if(user == null){

        }else{
            request.getSession().setAttribute("user",user);
        }
        return null;
    }

}
