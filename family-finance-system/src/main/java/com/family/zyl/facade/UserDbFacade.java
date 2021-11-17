package com.family.zyl.facade;

import cn.hutool.json.JSONObject;
import com.family.zyl.model.User;
import com.family.zyl.service.UserDbService;
import com.family.zyl.utils.JsonUtil;
import com.nari.zyl.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserDbFacade {

    @Autowired
    private UserDbService userDbService;

    //1.增加用户信息
    public CommonResult addUserInfo(User user) {
        CommonResult result = new CommonResult();
        //调用插入方法：
        int out = userDbService.addUserInfo(user);
        if(out >=1 ){
            result.setCode(1);
            result.setMessage("success");
        }else{
            result.setCode(0);
            result.setMessage("fail");
        }
        return result;
    }

    //2.删除用户信息:
    public CommonResult deleteUserInfo(String id) {
        CommonResult result = new CommonResult();
        int out = userDbService.deleteUserInfo(id);
        if(out >=0 ){
            result.setCode(1);
            result.setMessage("success");
        }else{
            result.setCode(0);
            result.setMessage("fail");
        }
        return result;
    }

    //3.修改用户信息：
    public CommonResult updateUserInfo(JSONObject jsonObject) {
        CommonResult result = new CommonResult();
        //(1).封装用户信息：
        String userStr = String.valueOf(jsonObject);
        List<User> users = JsonUtil.jsonToList(userStr,User.class);

        //(2).修改用户信息：
        int out = userDbService.updateUserInfo(users);
        if(out >=0 ){
            result.setCode(1);
            result.setMessage("success");
        }else{
            result.setCode(0);
            result.setMessage("fail");
        }
        return result;
    }

    //4.查询用户信息：
    public CommonResult selectAllUser() {
        CommonResult result = new CommonResult();
        List<User> users = userDbService.selectAllUser();
        if(users != null && users.size() >= 0){
            result.setCode(1);
            result.setData(users);
            result.setMessage("success");
        }else{
            result.setCode(0);
            result.setMessage("fail");
        }
        return result;
    }
}
