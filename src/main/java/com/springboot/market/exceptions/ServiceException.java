package com.springboot.market.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @getter,@setter,@equals@tostring等
@NoArgsConstructor // 生成无参构造
@AllArgsConstructor // 生成全参构造函数
public class ServiceException extends Exception {
    private  final static String  SERVICE_DEFAULT_CODE = "S00000";
    private String code;
    private String message;

    public  ServiceException( String message){
        super();
        this.code = SERVICE_DEFAULT_CODE;
        this.message = message;
    }
}
