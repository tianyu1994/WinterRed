package org.codeforworld.winterredserver.lang;/*
 *
 *@author: kfzx-menghj
 *@Time: 2020/7/25  17:39
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExcetionAdvice {
    @ExceptionHandler
    public Result exceptionHandler(HttpServletRequest request, Exception e){
        Result result = new Result();
        result.setErrorMsg(e.getMessage());
        logError(request, e);
        return result;
    }

    private static void logError(HttpServletRequest request, Exception e){
        log.error("url:{}|error:{}",request.getRequestURI(), e);
    }
    
}
