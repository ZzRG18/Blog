package com.zzrg.blog.handler;

import com.alibaba.fastjson.JSON;
import com.zzrg.blog.dao.pojo.SysUser;
import com.zzrg.blog.service.LoginService;
import com.zzrg.blog.utils.UserThreadLocal;
import com.zzrg.blog.vo.ErrorCode;
import com.zzrg.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在执行controller方法之前进行执行
        /**
         * 需要判断 请求的接口路径是不是 HandlerMethod 方法
         * 判断token是否为空 如果为空 未登录
         * 如果token不为空 验证loginService checkToken
         * 如果认证成功 放行
         */
        if (!(handler instanceof HandlerMethod)) {
            //静态目录放行
            //handler 可能是 RequestResourceHandler   springBoot 程序  访问静态资源 默认去classpath 下的static 目录查询
            return true;
        }
        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}", requestURI);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if (token == null) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        //是登录状态，放行
        //登录验证成功，放行
        //我希望在controller中 直接获取用户的信息 怎么获取?
        UserThreadLocal.put(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果用完不删会有内存泄漏的风险
        UserThreadLocal.remove();
    }
}
