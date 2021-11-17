package com.family.zyl.service;

import com.family.zyl.mapper.LoginDbMapper;
import com.family.zyl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginDbService {

    @Autowired
    private LoginDbMapper loginDbMapper;

    //登录
    public User judgeLoginUser(String userName, String passWord) {
        User user = null;
        try {
            user = loginDbMapper.getLogUserInfo(userName,passWord);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
