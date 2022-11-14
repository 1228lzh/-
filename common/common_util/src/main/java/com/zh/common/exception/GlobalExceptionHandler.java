package com.zh.common.exception;


import com.zh.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: ZH
 * @Date: 2022/11/6 21:42
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * @param e 异常
     * @return Result
     * 用了RestControllerAdvice，就不用加ResponseBody注解了
     */
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(HospitalException.class)
    public Result error(HospitalException e){
        e.printStackTrace();
        return Result.fail();
    }

}
