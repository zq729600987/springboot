package springboot.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import springboot.mapper.UserMapper;
import springboot.pojo.User;

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
    public String userLogin(String userno,String password){
        User user = userMapper.getUser(userno);
        if(user == null){
            return "";
        }
        if(!password.equals(user.getPassword())){
            return "";
        }
        //生成token
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("user_" + token, user,1, TimeUnit.HOURS);
        return token;
    }

    @Override
    public List<User> getUserList(Map<String,String> map) {
        //在你需要进行分页的 MyBatis select方法前调用 PageHelper.startPage 静态方法即可，紧跟在这个方法后的第一个select方法会被进行分页
        PageHelper.startPage(Integer.getInteger(map.get("pageNum")),Integer.getInteger(map.get("pageSize")));
        return userMapper.getUserList(map);
    }
}
