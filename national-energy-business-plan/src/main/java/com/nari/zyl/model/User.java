package com.nari.zyl.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProjectName: spring-cloud-fosun
 * @Package: com.vcs.basicapi.entity
 * @ClassName: BasicInfo
 * @Author: zhangs
 * @Email: 853632587@qq.com
 * @Description: 用户信息
 * @Date: 2020/12/3 14:27
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class User implements Serializable{

    private String name;

    private Integer age;

    private String port;

}
