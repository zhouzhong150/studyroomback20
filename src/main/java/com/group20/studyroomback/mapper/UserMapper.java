package com.group20.studyroomback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group20.studyroomback.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {

}
