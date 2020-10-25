package com.fashion.uaa.dao;

import com.fashion.uaa.entity.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    UserDO loadUserByUsername(@Param("username") String username);

    int insert(UserDO userDO);

    int update(UserDO userDO);

    int deleteById(@Param("id") String id);

}
