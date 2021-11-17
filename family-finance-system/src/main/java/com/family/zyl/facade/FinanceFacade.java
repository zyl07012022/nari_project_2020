package com.family.zyl.facade;

import cn.hutool.json.JSONObject;
import com.family.zyl.service.FinanceDbService;
import com.nari.zyl.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FinanceFacade {

    @Autowired
    private FinanceDbService financeDbService;

    public CommonResult addDebt(JSONObject jsonObject) {
        CommonResult result = new CommonResult();

        return  result;
    }

    public CommonResult deleteDebt(JSONObject jsonObject) {
        CommonResult result = new CommonResult();

        return  result;
    }

    public CommonResult updateDept(JSONObject jsonObject) {
        CommonResult result = new CommonResult();

        return  result;
    }

    public CommonResult selectAllDeptInfo() {
        CommonResult result = new CommonResult();

        return  result;
    }
}
