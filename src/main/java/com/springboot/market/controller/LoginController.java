package com.springboot.market.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.market.basecode.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(path = "/login.tml")
    public JSONObject module_login() {
        JSONObject robj = new JSONObject();
        JSONObject obj = getParamObj();
        // 获取参数
        String account = obj.getString("userName");
        String password = obj.getString("password");

        Map<String, Object> resultMap = loginService.login(account, password);
        if (resultMap == null) {
            // 登录失败
            return responseData("999998", "账号密码不匹配！", robj);
        } else {
            // 登录成功
            response.addHeader("X-Token", "ss");
            robj.put("items", resultMap);
            return responseData(robj);
        }
    }
}



































































