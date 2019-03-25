package springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springboot.pojo.User;
import springboot.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private RedisTemplate<String,Object> template;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    //MyWebMvcConfigurerAdapter中已配置
    /*@RequestMapping("/login")
    public String login(){
        return "login";
    }*/

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
