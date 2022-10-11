package com.mentpeak.website.handler;

import com.mentpeak.website.exception.PlatformException;
import com.mentpeak.website.util.Result;
import com.mentpeak.website.util.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestControllerAdvice
public class PlatformExceptionHandler {

    /**
     * 全局异常捕捉处理
     */
    @ExceptionHandler(value = {Exception.class})
    public Result handlerException(Exception exception, HttpServletRequest request) {
        log.error("请求路径uri={},系统内部出现异常:{}", request.getRequestURI(), exception);
        Result result = Result.error(ResultEnum.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage());
        return result;
    }


    /**
     * 自定义异常-PlatformException
     */
    @ExceptionHandler(value = {PlatformException.class})
    public Result handlerCustomException(PlatformException exception, HttpServletRequest request) {
        log.error("请求路径uri={},出现异常:{}", request.getRequestURI(), exception);
        String errorMessage = exception.getMsg();
        Result result = Result.error(exception.getCode(), errorMessage);
        return result;
    }
}
