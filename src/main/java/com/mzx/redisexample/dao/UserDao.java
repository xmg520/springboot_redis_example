package com.mzx.redisexample.dao;

import com.mzx.redisexample.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 查询所有用户接口
     */
    List<User> findUserAll();
}
