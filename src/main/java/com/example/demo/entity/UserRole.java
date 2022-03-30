package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import com.example.demo.base.BaseEntity;


@Data
@ApiModel("")
public class UserRole extends BaseEntity<UserRole> {


                private String userid;
    
                private String roleid;
    
}

