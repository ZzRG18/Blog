package com.zzrg.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzrg.blog.dao.pojo.SysUser;
import com.zzrg.blog.service.LoginService;
import com.zzrg.blog.service.SysUserService;
import com.zzrg.blog.utils.JWTUtils;
import com.zzrg.blog.vo.ErrorCode;
import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    //需要查询用户的表
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //加密盐用于加密
    private static final String slat="ZzRG!@###";

    @Override
    public Result login(LoginParam loginParam) {

        /**
         * 检查参数是否合法
         * 根据用户名和密码去User表中查询 是否安全
         * 如果不存在 登陆失败
         * 如果存在 使用JWT 生成token 返回前端
         * 最好 把token放入到redis当中 token中的User信息设置过期时间
         * （登陆认证的时候 先认证token字符串是否合理，去redis认证是否存在）
         */

        String account = loginParam.getAccount();
        String password = loginParam.getPassword();

        if (StringUtils.isBlank(account)||StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        //处理加密信息
        String pwd = DigestUtils.md5Hex(password + slat);

        //在User表中查询用户
        SysUser sysUser = sysUserService.findUser(account,pwd);
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //存在
        //登录成功，使用JWT生成token，返回token和redis中
        String token = JWTUtils.createToken(sysUser.getId());
        //放入redis中
        //过期时间是一天
        redisTemplate.opsForValue().set("TOKEN_"+token,JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        //token为空返回null
        if (StringUtils.isBlank(token)){
            return null;
        }
        //解析失败
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap ==null){
            return null;
        }
        //如果成功
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return null;
        }
        //解析回sysUser对象
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;

    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }

    /**
     * 注册服务逻辑
     * @param loginParam
     * @return
     */
    @Override
    public Result register(LoginParam loginParam) {

        /**
         * 判断参数是否合法
         * 判断账户是否存在，存在 返回存在
         * 不存在 注册用户
         * 生成token
         * 存入redis 并返回
         * 注意 加上事务 一旦中间的任何过程出现问题 注册用户 需要回滚
         *
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account)
            || StringUtils.isBlank(password)
            || StringUtils.isBlank(nickname)) {

            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = this.sysUserService.findUserByAccount(account);
        if (sysUser !=null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),"账户已经被注册了");
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+slat));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        this.sysUserService.save(sysUser);

        //token
        String token = JWTUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    //生成我们想要的密码，放于数据库用于登陆
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("admin"+slat));
    }

}
