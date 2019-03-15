package com.springboot.market.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.market.basecode.entity.UserInfo;
import com.springboot.market.basecode.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/getUserInfo.tml")
    public JSONObject template_getUserInfo() {
        JSONObject obj = getParamObj();

        UserInfo userInfo = new UserInfo();
        userInfo.setName(obj.getString("name"));
        userInfo.setId(obj.getLong("id"));
        userInfo.setSex(obj.getString("sex"));

        JSONObject robj = userInfoService.getUserInfo(userInfo);
        log.debug("robj:" + robj.toJSONString());
        return responseData(robj);
    }
}

































