package com.family.zyl.mapper;

import com.family.zyl.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginDbMapper {

     User getLogUserInfo(@Param("userName") String userName, @Param("passWord") String passWord);

}
