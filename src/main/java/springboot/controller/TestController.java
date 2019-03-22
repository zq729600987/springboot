package springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.pojo.User;
import springboot.service.UserService;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private RedisTemplate<String,Object> template;

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/index")
    public String index(){
        return "hello";
    }

    @RequestMapping("/userList")
    public String getUserList(Model m){
        List<User> list = userService.getUserList();
        m.addAttribute("list",list);
        return "hello";
    }

    @RequestMapping("/redis")
    public String test(){
        /*System.out.println(redisTemplate.opsForList().leftPushAll("test",1,2,3,4,5,6));
        redisTemplate.opsForList().leftPop("test",1000, TimeUnit.SECONDS);*/
        User user = userService.getUser();
        template.opsForHash().put("user",user.getId(),user);
        logger.info("--------拿到user--------");
        User getUser = (User)template.opsForHash().get("user",user.getId());
        return "hello";
    }

}
