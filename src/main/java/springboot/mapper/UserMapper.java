package springboot.mapper;

import springboot.pojo.User;
import springboot.util.CommonMapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends CommonMapper<User> {
    //只要配置MyBatis时能注册或者扫描到通用接口，继承的通用接口提供的方法都可以使用，也可新增方法
    User getUser(String username);
    List<User> getUserList(Map<String,String> map);

    //在接口中，只要不是通过注解来实现接口方法，接口是允许重名的，真正调用会使用通用 Mapper 提供的方法
    //在对应的 XML 中，不能出现和继承接口中同名的方法
    //List<User> selectAll(Integer id);
}
