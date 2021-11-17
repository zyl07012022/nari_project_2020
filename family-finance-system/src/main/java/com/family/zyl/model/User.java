package com.family.zyl.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class User {

   @JSONField(name="id")
   private String id;

   @JSONField(name="name")
   private String name ;

   @JSONField(name="role_id")
   private String roleId ;

   @JSONField(name="position")
   private String position;

   @JSONField(name="image")
   private String image;

   @JSONField(name="sex")
   private String sex;

   @JSONField(name="birth")
   private String birth;

   @JSONField(name="mobile")
   private String mobile;

   @JSONField(name="address")
   private String address;

}
