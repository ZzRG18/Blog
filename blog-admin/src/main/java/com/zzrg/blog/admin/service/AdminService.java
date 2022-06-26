package com.zzrg.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzrg.blog.admin.mapper.AdminMapper;
import com.zzrg.blog.admin.mapper.PermissionMapper;
import com.zzrg.blog.admin.pojo.Admin;
import com.zzrg.blog.admin.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/25
 */
@Service
public class AdminService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private AdminMapper adminMapper;

    public Admin findAdminByUserName(String username){
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername,username);
        queryWrapper.last("limit 1");
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    public List<Permission> findPermissionsByAdminId(Long adminId){
        return permissionMapper.findPermissionsByAdminId(adminId);
    }
}
