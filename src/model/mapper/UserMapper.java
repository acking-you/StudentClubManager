package model.mapper;

import model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select count(*) from users where " +
            "username=#{userName} and password=#{password}")
    int isUserExist(User user);

    @Update("update users set password=#{password} where username=#{username}")
    int modifyPassword(@Param("username") String userName, @Param("password") String password);
}
