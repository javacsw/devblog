package org.example.backend.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.backend.Entity.pojp.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM tb_users WHERE username = #{username} AND password = #{password}")
    User getUserByUsernameAndPassword(String username,String password);

    @Select("SELECT username FROM tb_users WHERE id=#{id}")
    String getUsernameById(Integer id);
    @Select("SELECT * FROM tb_users WHERE id = #{id}")
    User getUserById(Integer id);


    @Select("SELECT avator FROM  tb_users WHERE id=#{id}")
    String getAvatorById(Integer id);
}
