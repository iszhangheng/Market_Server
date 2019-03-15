// package com.springboot.market.filters;
//
// import com.alibaba.fastjson.JSONObject;
// import com.springboot.market.util.secret.JwtTools;
// import lombok.extern.slf4j.Slf4j;
//
// import javax.servlet.*;
// import javax.servlet.annotation.WebFilter;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
//
// /**
//  * @program: Market
//  * @author: Mr.Zhang
//  * @email iszhangheng@foxmail.com
//  * @create: 2019-03-07 15:58
//  * @description: 登录验证过滤器
//  **/
// @Slf4j
// @WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
// public class AuthFilter implements Filter {
//
//     @Override
//     public void init(FilterConfig filterConfig) throws ServletException {
//
//     }
//
//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//             throws IOException, ServletException {
//         try {
//             HttpServletRequest req = (HttpServletRequest) request;
//             String xToken = req.getHeader("X-Token");
//             log.debug("xToken" + xToken);
//             if ("flow=login".equals(req.getQueryString()) || JwtTools.checkToken(xToken)) {// 当登录操作或验证通过时进入下一过滤器
//                 if (!"undefined".equals(xToken) && xToken != null && !xToken.isEmpty()) { // 当非登录操作且验证通过时更新令牌有效时间
//                     ((HttpServletResponse) response).setHeader("X-Token", JwtTools.updateDateToken(xToken, JwtTools.getEXP()));
//                 }
//                 chain.doFilter(request, response);
//             } else {
//                 returnResult(response);
//             }
//         } catch (Exception e) {
//             returnResult(response);
//             log.debug(e.getMessage());
//             log.debug("Token过滤器异常");
//         }
//     }
//
//     /**
//      * 在未登录状态下发送的请求返回方法
//      *
//      * @param response
//      * @throws IOException
//      */
//     private void returnResult(ServletResponse response) throws IOException {
//         response.setCharacterEncoding("UTF-8");
//         response.setContentType("application/json; charset=utf-8");
//         JSONObject dataJson = new JSONObject();
//         dataJson.put("rcode", "999999");
//         dataJson.put("msg", "登录超时，请重新登录");
//         log.debug("dataJson:" + dataJson.toString());
//         response.getWriter().append(dataJson.toJSONString());
//     }
//
//     @Override
//     public void destroy() {
//
//     }
//
// }
