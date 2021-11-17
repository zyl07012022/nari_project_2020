package com.family.zyl.controller;

import cn.hutool.json.JSONObject;
import com.family.zyl.facade.AccountDbFacade;
import com.family.zyl.facade.FinanceFacade;
import com.family.zyl.facade.LoginFacade;
import com.family.zyl.facade.UserDbFacade;
import com.family.zyl.model.User;
import com.nari.zyl.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Resource
    private LoginFacade loginFacade;
    @Resource
    private UserDbFacade userDbFacade;
    @Resource
    private AccountDbFacade accountDbFacade;
    @Resource
    private FinanceFacade financeFacade;

    /******************************** 一、登录 **********************************/
    //(1).用户登录：
    @PostMapping("/access/login")
    private CommonResult login(@RequestBody JSONObject jsonObject){
        return loginFacade.login(jsonObject);
    }

    //(2).修改登录密码(md5)：
    @PostMapping("/access/updatePassword")
    private CommonResult updatePassword(@RequestBody JSONObject jsonObject){
        return loginFacade.updatePassword(jsonObject);
    }

    //(3).退出登录：
    @PostMapping("/access/loginOut")
    private CommonResult loginOut(@RequestBody JSONObject jsonObject){
        return loginFacade.loginOut(jsonObject);
    }

    /******************************** 二、家庭成员信息管理 ************************/
    //(4).增加用户信息:
    @PostMapping("/user/addUser")
    private CommonResult addUserInfo(@RequestBody User user){
        return userDbFacade.addUserInfo(user);
    }

    //(5).删除用户信息:
    @GetMapping("/user/deleteUser")
    private CommonResult deleteUserInfo(String id){
        return userDbFacade.deleteUserInfo(id);
    }

    //(6).修改用户信息：
    @PostMapping("/user/updateUser")
    private CommonResult updateUserInfo(@RequestBody JSONObject jsonObject){
        return userDbFacade.updateUserInfo(jsonObject);
    }

    //(7).查询所有用户信息：
    @PostMapping("/user/getAllUser")
    private CommonResult selectAllUser(){
        return userDbFacade.selectAllUser();
    }

    /******************************** 三、账户信息管理 ***************************/
    //(8).增加账户
    @PostMapping("/account/addAccount")
    private CommonResult addAccount(@RequestBody JSONObject jsonObject){
        return accountDbFacade.addAccount(jsonObject);
    }

    //(9).删除账户
    @PostMapping("/account/deleteAccount")
    private CommonResult deleteAccount(@RequestBody JSONObject jsonObject){
        return accountDbFacade.deleteAccount(jsonObject);
    }

    //(10).修改账户
    @PostMapping("/account/updateAccount")
    private CommonResult updateAccount(@RequestBody JSONObject jsonObject){
        return accountDbFacade.updateAccount(jsonObject);
    }

    //(11).查询账户
    @PostMapping("/account/selectAccount")
    private CommonResult selectAllAccount(@RequestBody JSONObject jsonObject){
        return accountDbFacade.selectAllAccount(jsonObject);
    }

    /******************************** 四、家庭成员收支情况管理 ***************************/
    //(12).记账(收支情况)：
    @PostMapping("/dept/addDebt")
    private CommonResult addDebt(@RequestBody JSONObject jsonObject){
        return financeFacade.addDebt(jsonObject);
    }

    //(13).删除收支记录：
    @PostMapping("/dept/deleteDebt")
    private CommonResult deleteDebt(@RequestBody JSONObject jsonObject){
        return financeFacade.deleteDebt(jsonObject);
    }

    //(14).修改收支记录：
    @PostMapping("/dept/updateDept")
    private CommonResult updateDept(@RequestBody JSONObject jsonObject){
        return financeFacade.updateDept(jsonObject);
    }

    //(15).查询记收支记录(统计)：
    @PostMapping("/dept/selectAllDeptInfo")
    private CommonResult selectAllDeptInfo(){
        return financeFacade.selectAllDeptInfo();
    }

}
