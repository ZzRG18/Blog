package com.zzrg.blog.handler;

import com.zzrg.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
//全局异常处理
//对加了@Controller注解的方法进行拦截处理 AOP的实现
@ControllerAdvice
public class AllExceptionHandler {

    //进行异常处理，处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回json数据如果不加就返回页面了
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-400,"系统异常！");
    }
}
