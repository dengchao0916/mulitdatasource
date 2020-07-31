package com.example.multidatasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.multidatasource.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : dengchao
 * @date : 2020 07 31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void insertUser(User user);
}
