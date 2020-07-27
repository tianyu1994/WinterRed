package org.codeforworld.winterredserver.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
@Aspect
public class RecordUserAspect {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getCurUser(){
        return threadLocal.get();
    }

    @Pointcut("execution (* org.codeforworld.winterredserver.controller..*.*(..))")
    public void getUserPorintcut(){}

    @Before("getUserPorintcut()")
    public void getUser() throws UnsupportedEncodingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request != null){
            //保存操作人
            String userName = request.getHeader("USER_NAME") == null ? "" : request.getHeader("USER_NAME");
            threadLocal.set(URLDecoder.decode(userName, "utf-8"));
        }
    }

    @After("getUserPorintcut()")
    public void removeUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request != null){
            //移除操作人
            threadLocal.remove();
        }
    }
}
