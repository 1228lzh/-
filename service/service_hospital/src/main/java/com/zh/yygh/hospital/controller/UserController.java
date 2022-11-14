package com.zh.yygh.hospital.controller;

import com.zh.common.result.R;
import com.zh.common.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @Author: ZH
 * @Date: 2022/11/8 20:18
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }


    //info
    @GetMapping("info")
    public R getInfo(){
        return R.ok().data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://img03.sogoucdn.com/app/a/100520093/d71a6360ba8601ff-19264876bfd6a308-dfbd047d3bb4f2621f01d7ae18979b6e.jpg");
    }
}
