package springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springboot.pojo.User;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper{
    List<User> getUserList(Map<String,String> map);
    User getUser(String username);
}
