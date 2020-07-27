package org.codeforworld.winterredserver.lang;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private int code;
    private String msg;
    public ApiException(){
        this(CodeConstant.RETCODE_500, "接口错误");
    }

    public ApiException(String msg){
        this(CodeConstant.RETCODE_500, msg);
    }

    public ApiException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
