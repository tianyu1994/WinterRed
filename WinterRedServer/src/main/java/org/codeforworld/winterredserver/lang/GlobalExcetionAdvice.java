package org.codeforworld.winterredserver.lang;/*
 *
 *@author: kfzx-menghj
 *@Time: 2020/7/25  17:39
 */

import com.alibaba.fastjson.JSONException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExcetionAdvice {
    private static void logError(HttpServletRequest request, Exception e){
        log.error("url:{}|error:{}", request.getRequestURI(), e);
    }

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest request, Exception e){
        Result result = new Result();
        result.setMsg(e.getMessage());
        logError(request, e);
        return result;
    }

    @ExceptionHandler(value = ApiException.class)
    public Result apiExceptionHandler(HttpServletRequest request, ApiException e){
        Result result = new Result();
        result.setMsg(e.getMsg());
        result.setRetCode(e.getCode());
        logError(request, e);
        return result;
    }

    @ExceptionHandler(value = JSONException.class)
    public Result jsonErrorExceptionHandler(HttpServletRequest request, JSONException e){
        Result result = new Result();
        result.setMsg(e.getMessage());
        logError(request, e);
        return result;
    }

    /**
     * 捕捉任何参数不传导致的异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result doHttpMessageNotReadableExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException e){
        Result result = new Result();
        result.setMsg(e.getMessage());
        logError(request, e);
        return result;
    }

    /**
     * 捕捉参数不合法异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result doValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e){
        Result result = new Result();
        //从异常对象中拿到ObjectError对象
        List<ObjectError> objectErrorList = e.getBindingResult().getAllErrors();
        StringBuffer errorFields = new StringBuffer();
        for (ObjectError objectError : objectErrorList) {
            if(!StringUtils.isEmpty(errorFields.toString())){
                errorFields.append(",").append(objectError.getDefaultMessage());
            }else {
                errorFields.append("以下参数不合法：").append(objectError.getDefaultMessage());
            }
        }
        //然后提取错误提示信息进行返回
        result.setErrorMsg(errorFields.toString());
        result.setRetCode(CodeConstant.RETCODE_405);
        logError(request, e);
        return result;
    }

    /**
     * form表达非空字段未传值抛出的异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public Result doHttpMessageNotReadableExceptionHandler(HttpServletRequest request, BindException e){
        Result result = new Result();
        //从异常对象中拿到ObjectError对象
        List<ObjectError> objectErrorList = e.getBindingResult().getAllErrors();
        StringBuffer errorFields = new StringBuffer();
        for (ObjectError objectError : objectErrorList) {
            if(!StringUtils.isEmpty(errorFields.toString())){
                errorFields.append(",").append(objectError.getDefaultMessage());
            }else {
                errorFields.append(objectError.getDefaultMessage());
            }
        }
        //然后提取错误提示信息进行返回
        result.setErrorMsg(errorFields.toString());
        result.setRetCode(CodeConstant.RETCODE_405);
        logError(request, e);
        return result;
    }

}
