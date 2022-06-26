package com.zzrg.blog.admin.service;

import com.zzrg.blog.admin.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/25
 */
@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //通过登录的时候，会把username 传递到这里
        //根据用户名 查找用户，不存在 抛出异常，
        // 存在 将用户名，密码，授权列表 组装成springSecurity的User对象 并返回
        Admin admin = this.adminService.findAdminByUserName(username);
        if (admin == null){
            //登陆失败
            return null;
//            throw new UsernameNotFoundException("用户名不存在");
        }
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        UserDetails userDetails = new User(username,admin.getPassword(), authorities);
        //剩下的认证 就由框架帮我们完成
        return userDetails;

    }

    //测试时输出的密码
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}
