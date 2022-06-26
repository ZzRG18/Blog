package com.zzrg.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;



/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
@Data
public class SysUser {

   // @TableId(type= IdType.ASSIGN_ID)//默认id类型
    //以后用户多了就需要 使用分布式id了
//    TableId(Type =IdType.AUTO) 数据自增
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
