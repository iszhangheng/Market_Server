package com.springboot.market.basecode.mapper;

import com.springboot.market.basecode.entity.AuthInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuthInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthInfo record);

    AuthInfo selectByPrimaryKey(Long id);

    List<AuthInfo> selectAll();

    int updateByPrimaryKey(AuthInfo record);

    /**
     * 登陆接口
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    Map<String, Object> login(@Param("account") String account, @Param("password") String password);
}