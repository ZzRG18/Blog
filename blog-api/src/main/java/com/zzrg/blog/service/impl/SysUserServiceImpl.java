package com.zzrg.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzrg.blog.dao.mapper.SysUserMapper;
import com.zzrg.blog.dao.pojo.SysUser;
import com.zzrg.blog.service.LoginService;
import com.zzrg.blog.service.SysUserService;
import com.zzrg.blog.vo.ErrorCode;
import com.zzrg.blog.vo.LoginUserVo;
import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private LoginService loginService;

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        //防止findUserById为空
        if (sysUser ==null){
            sysUser = new SysUser();
            sysUser.setNickname("ZzRG");
        }
        return sysUser;
    }

    @Override
    public UserVo findUserVoById(Long userVoId) {
        SysUser sysUser = sysUserMapper.selectById(userVoId);
        //防止findUserById为空
        if (sysUser ==null){
            sysUser = new SysUser();
            sysUser.setNickname("ZzRG");
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(sysUser,userVo);
        userVo.setId(String.valueOf(sysUser.getId()));
        return userVo;
    }

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getId,SysUser::getAccount,SysUser::getAvatar,SysUser::getNickname);
        queryWrapper.last("limit 1");
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }

    @Override
    public Result getUserInfoByToken(String token) {
        /**
         *token的合法性校验
         * 是否为空 ，解析是否为空 redis是否存在
         * 如果校验失败 返回错误
         *如果成功，返回对应的结果 LoginUserVo
         */

        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(String.valueOf(sysUser.getId()));
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setAccount(sysUser.getAccount());
        return Result.success(loginUserVo);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.last("limit 1");
        return this.sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        //保存用户 id自动生成
        //这个地方 默认生成的id是分布式的 雪花算法出来的
        //mybatis-Plus
        this.sysUserMapper.insert(sysUser);
    }


}
