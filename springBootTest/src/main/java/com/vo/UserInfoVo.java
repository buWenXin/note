package com.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoVo {
    private  String userName;
    private  String  sex;
    private  String age;
}
