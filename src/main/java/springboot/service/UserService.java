package springboot.service;


import springboot.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getUser(String username);
}
