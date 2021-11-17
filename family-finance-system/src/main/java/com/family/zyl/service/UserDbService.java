package com.family.zyl.service;

import com.family.zyl.mapper.UserDbMapper;
import com.family.zyl.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserDbService {

    @Autowired
    private UserDbMapper userDbMapper;

    //1.增加用户信息
    public int addUserInfo(User user) {
        int result = 0;
        try {
            result = userDbMapper.addUserInfo(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //2.删除用户信息:
    public int deleteUserInfo(String id) {
        int result = -1;
        try {
            result = userDbMapper.deleteUserInfo(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //3.修改用户信息：
    public int updateUserInfo(List<User> users) {
        int result = -1;
        try {
            result = userDbMapper.updateUserInfo(users);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //4.查询用户信息：
    public List<User> selectAllUser() {
        List<User> users = null;
        try {
            users =  userDbMapper.selectAllUser();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
}
