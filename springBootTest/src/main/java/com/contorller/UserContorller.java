package com.contorller;

import com.entity.UserInfo;
import com.service.UserService;
import com.utis.Result;
import com.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserContorller {
    @Autowired
    private UserService userService;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    public Result<UserInfoVo> test() {
        List<UserInfo> list = userService.queryAllUserList();
        return Result.success(list);
    }
}
