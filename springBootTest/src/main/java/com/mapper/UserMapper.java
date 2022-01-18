package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.UserInfo;
import com.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
}
