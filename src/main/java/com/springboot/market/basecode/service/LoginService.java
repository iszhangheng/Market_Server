package com.springboot.market.basecode.service;

import com.springboot.market.basecode.mapper.AuthInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("loginService")
public class LoginService {

    @Resource
    private AuthInfoMapper authInfoMapper;

    public Map<String, Object> login(String account, String password) {
        return authInfoMapper.login(account, password);
    }
}
