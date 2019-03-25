package springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import springboot.pojo.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper{

    @Select("select * from user")
    List<User> getUserList();

    @Select("select * from user where username = #{username}")
    User getUser(@Param("username") String username);
}
