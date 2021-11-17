package com.nari.zyl.entities;

public class ExceptionConstant {

    public static final String SYSTEM_ERROR = "SYSTEM_ERROR";

    public static final ExceptionInfo DATABASE_ERROR = new ExceptionInfo("-1","数据库数据异常");

    public static final ExceptionInfo SUCCESS = new ExceptionInfo("0", "成功");

    public static final ExceptionInfo WSGW_SUCCESS = new ExceptionInfo("1", "成功");

    public static final ExceptionInfo PRAMETER_NULL = new ExceptionInfo("2", "参数不能为空");

    public static final ExceptionInfo RECHARGE_ALREADY_EXISTS = new ExceptionInfo("100001", "充值任务已经存在");
    public static final ExceptionInfo LICENSE_ALREADY_USED = new ExceptionInfo("100002", "LICENSE已被使用");
    public static final ExceptionInfo LICENSE_INVALID = new ExceptionInfo("100003", "LICENSE无效");
    public static final ExceptionInfo SENDVALUE_ILLEGAL = new ExceptionInfo("100004", "充值的金额不合法");
    public static final ExceptionInfo RECHARGE_NOT_EXISTS = new ExceptionInfo("100005", "充值任务不存在");
    public static final ExceptionInfo  FEIGN_RESPONSE_IS_NULL= new ExceptionInfo("100006", "feign返回结果为空");

    public static ExceptionInfo genSystemException(Throwable throwable) {
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setErrorCode(SYSTEM_ERROR);
        exceptionInfo.setErrorMessage(throwable.getMessage());
        return exceptionInfo;
    }

}
