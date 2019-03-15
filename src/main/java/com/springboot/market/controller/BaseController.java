package com.springboot.market.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.market.util.format.DateTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j // 相当于 public static final Logger log =
		// LogManager.getLogger(BaseController.class);
@Controller
public class BaseController {
	@Autowired
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	/**
	 * 获取封装参数的JSONObject
	 *
	 * @return 封装请求参数的JSONObject
	 */
	public JSONObject getParamObj() {
		return handlerData();
	}

	/**
	 * 从request的输入流中读取Content-Type:Application/JSon传值
	 *
	 * @return 存储请求参数的json对象
	 */
	private JSONObject handlerData() {
		// 存储从输入流中读取的数据
		StringBuffer stringBuffer = new StringBuffer();
		try {
			InputStream is = request.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF8"));
			String s = "";
			while ((s = bufferedReader.readLine()) != null) {
				stringBuffer.append(s);
			}
			is.close();
			bufferedReader.close();
		} catch (IOException e) {
			log.debug("参数输入流读取错误！");
			log.debug("error:" + e.getMessage());
		}
		return JSONObject.parseObject(stringBuffer.toString());
	}

	/**
	 * 包装返回数据的格式
	 *
	 * @param rcode 状态码
	 * @param msg   错误信息
	 * @param robj  返回数据
	 * @return
	 */
	protected JSONObject responseData(String rcode, String msg, JSONObject robj) {
		JSONObject dataJson = new JSONObject();// 包装请求返回的数据
		if (request != null) {
			String path = request.getRequestURI();
			dataJson.put("transCode", path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".tml")));
		} else {
			dataJson.put("transCode", "other");
		}
		dataJson.put("rcode", rcode); // 状态码
		dataJson.put("rtime", DateTools.currentDate()); // 请求时间
		dataJson.put("msg", msg); // 报错信息
		log.debug("dataJson:" + dataJson.toString());
		dataJson.put("robj", robj);
		return dataJson;
	}

	protected JSONObject responseData(JSONObject robj) {
		return responseData("000000", null, robj);
	}

	protected JSONObject responseData(String msg) {
		return responseData("999999", msg, null);
	}
}
