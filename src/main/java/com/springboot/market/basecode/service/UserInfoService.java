package com.springboot.market.basecode.service;

import com.alibaba.fastjson.JSONObject;
import com.springboot.market.basecode.entity.UserInfo;
import com.springboot.market.basecode.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userInfoService")
public class UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    public JSONObject getUserInfo(UserInfo userInfo) {
        JSONObject robj = new JSONObject();
        UserInfo resultUserInfo = userInfoMapper.selectByPrimaryKey(userInfo.getId());
        robj.put("items", resultUserInfo);
        return robj;
    }
}


















