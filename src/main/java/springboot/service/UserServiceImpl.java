package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.mapper.UserMapper;
import springboot.pojo.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public User getUser(){
        return userMapper.getUser();
    }
}
