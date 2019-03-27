package springboot.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import springboot.pojo.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper{
    List<User> getUserList();
    User getUser(@Param("username") String username);
}
