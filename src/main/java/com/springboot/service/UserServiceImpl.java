package com.springboot.service;

import com.github.pagehelper.PageHelper;
import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;
import com.utils.DynamicBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DynamicBean userLogin(String userno, String password){
        DynamicBean back = new DynamicBean();
        User user = userMapper.getUser(userno);
        if(user == null){
            back.put("returncode","1");
            back.put("returnmsg","用户不存在");
            return back;
        }
        if(!password.equals(user.getPassword())){
            back.put("returncode","1");
            back.put("returnmsg","用户名或密码错误");
            return back;
        }
        //生成token
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("user_" + token, user,1, TimeUnit.HOURS);

        back.put("returncode","0");
        back.put("token",token);
        return back;
    }

    @Override
    public List<User> getUserList(Map<String,String> map) {
        //在你需要进行分页的 MyBatis select方法前调用 PageHelper.startPage 静态方法即可，紧跟在这个方法后的第一个select方法会被进行分页
        PageHelper.startPage(Integer.getInteger(map.get("pageNum")),Integer.getInteger(map.get("pageSize")));
        return userMapper.getUserList(map);
    }
}
