package com.springboot.service;


import com.springboot.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    String userLogin(String userno,String password);
    List<User> getUserList(Map<String,String> map);
}
