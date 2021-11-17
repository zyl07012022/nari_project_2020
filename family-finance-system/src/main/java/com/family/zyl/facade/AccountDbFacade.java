package com.family.zyl.facade;

import cn.hutool.json.JSONObject;
import com.family.zyl.service.AccountDbService;
import com.nari.zyl.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDbFacade {

    @Autowired
    private AccountDbService accountDbService;


    public CommonResult addAccount(JSONObject jsonObject) {
        CommonResult result = new CommonResult();

        return  result;
    }

    public CommonResult deleteAccount(JSONObject jsonObject) {
        CommonResult result = new CommonResult();

        return  result;
    }

    public CommonResult updateAccount(JSONObject jsonObject) {
        CommonResult result = new CommonResult();

        return  result;
    }

    public CommonResult selectAllAccount(JSONObject jsonObject) {
        CommonResult result = new CommonResult();

        return  result;
    }
}
