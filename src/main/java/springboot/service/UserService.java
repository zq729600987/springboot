package springboot.service;


import springboot.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getUserList(Map<String,String> map);
    User getUser(String username);
}
