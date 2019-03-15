package com.springboot.market.exceptions;

/**
 * @program: Market
 * @author: Mr.Zhang
 * @email iszhangheng@foxmail.com
 * @create: 2019-03-11 10:41
 * @description: 异常抽象类
 **/
public abstract class BaseException extends Exception {
    String code;
    String message;
}
