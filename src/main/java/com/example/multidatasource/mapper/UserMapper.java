package com.example.multidatasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.multidatasource.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : dengchao
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void insertUser(User user);
}
