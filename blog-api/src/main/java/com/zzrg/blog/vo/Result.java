package com.zzrg.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */

//连接返回提示用于判断
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private boolean success;

    private Integer code;

    private String msg;

    private Object data;

    //连接时候的返回值
    //success
    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }
    //fail
    public static Result fail(Integer code,String msg){
        return new Result(false,code,msg,null);
    }


}
