package springboot.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.mapper.UserMapper;
import springboot.pojo.User;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList(Map<String,String> map) {
        //在你需要进行分页的 MyBatis select方法前调用 PageHelper.startPage 静态方法即可，紧跟在这个方法后的第一个select方法会被进行分页
        PageHelper.startPage(Integer.getInteger(map.get("pageNum")),Integer.getInteger(map.get("pageSize")));
        return userMapper.getUserList(map);
    }

    @Override
    public User getUser(String username){
        return userMapper.getUser(username);
    }
}
