package springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springboot.pojo.User;
import springboot.service.UserService;

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
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response,ModelAndView mv){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUser(username);
        if(user == null){
            mv.setViewName("login");
        }else{
            request.getSession().setAttribute("user",user);
            mv.setViewName("index");
        }
        mv.addObject("");
        return mv;
    }

    @RequestMapping("/redis")
    public String test(){
        /*System.out.println(redisTemplate.opsForList().leftPushAll("test",1,2,3,4,5,6));
        redisTemplate.opsForList().leftPop("test",1000, TimeUnit.SECONDS);*/
        /*User user = userService.getUser();
        template.opsForHash().put("user",user.getId(),user);
        logger.info("--------拿到user--------");
        User getUser = (User)template.opsForHash().get("user",user.getId());*/
        return "hello";
    }

}
