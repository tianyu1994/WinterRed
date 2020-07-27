package org.codeforworld.winterredserver.lang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
 * 统一返回值类型
 *@author: kfzx-menghj
 *@Time: 2020/7/25  17:43
 */

@RestControllerAdvice(basePackages = {"org.codeforworld.winterredserver.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Result result = new Result();
        if(returnType.getGenericParameterType().equals(String.class)){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                result.setResults(data);
                return objectMapper.writeValueAsString(result);
            }catch (JsonProcessingException e){
                throw new RuntimeException("返回的String类型错误");
            }
        }else if(returnType.getGenericParameterType().equals(Result.class)){
            return data;
        }else {
            result.setResults(data);
            return result;
        }
    }
}
