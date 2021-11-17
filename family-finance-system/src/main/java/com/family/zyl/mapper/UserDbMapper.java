package com.family.zyl.mapper;

import com.family.zyl.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDbMapper {

     //用户信息管理
     int addUserInfo(@Param("user") User user);

     int deleteUserInfo(@Param("id")String id);

     int updateUserInfo(@Param("users") List<User> users);

     List<User> selectAllUser();

}
