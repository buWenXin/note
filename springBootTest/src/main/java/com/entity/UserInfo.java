package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.PipedReader;
import java.util.Date;

@Data
@TableName("user")
public class UserInfo {
    @TableId(value = "",type = IdType.AUTO)
    private Integer Id;
    private Date CreateTime;
    private Date UpdateTime;
    private Integer IsDelete;
    private Date DeleteTime;
    private String UserName;
    private String Password;
    private String Mobile;
}
