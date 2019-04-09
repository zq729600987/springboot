package springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.pojo.User;
import springboot.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserList")
    public Map<String,Object> getUserList(HttpServletRequest request, HttpServletResponse response){
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");

        Map<String,String> param = new HashMap<>();
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        List<User> userList = userService.getUserList(param);
        //用PageInfo对结果进行包装
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        JSONObject obj = new JSONObject();
        obj.put("rows",userList);
        obj.put("pages",pageInfo.getPages());   //总页数
        obj.put("counts",pageInfo.getTotal());  //总记录数
        return obj;
    }
}
