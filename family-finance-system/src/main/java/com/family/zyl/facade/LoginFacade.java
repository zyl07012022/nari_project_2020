package com.family.zyl.facade;

import cn.hutool.json.JSONObject;
import com.family.zyl.model.User;
import com.family.zyl.service.LoginDbService;
import com.nari.zyl.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginFacade {

    @Autowired
    private LoginDbService loginDbService;

    //(1).用户登录：
    public CommonResult login(JSONObject jsonObject) {
        CommonResult result = new CommonResult();
        String userName = jsonObject.getStr("userName");
        String passWord = jsonObject.getStr("passWord");

        User loginUser = loginDbService.judgeLoginUser(userName,passWord);
        if(loginUser != null && !"".equals(loginUser.getName())){
            result.setCode(1);
            result.setMessage("success");
        }else{
            result.setCode(0);
            result.setMessage("fail");
        }
        return result;
    }

    //(2).修改登录密码：
    public CommonResult updatePassword(JSONObject jsonObject) {
        CommonResult result = new CommonResult();
        return result;
    }

    //(3).退出登录：
    public CommonResult loginOut(JSONObject jsonObject) {
        CommonResult result = new CommonResult();


        return result;
    }
}
