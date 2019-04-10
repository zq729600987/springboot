package com.springboot.service;


import com.springboot.pojo.User;
import com.utils.DynamicBean;

import java.util.List;
import java.util.Map;

public interface UserService {
    DynamicBean userLogin(String userno, String password);
    List<User> getUserList(Map<String,String> map);
}
