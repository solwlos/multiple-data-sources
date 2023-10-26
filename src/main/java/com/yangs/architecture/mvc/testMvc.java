package com.yangs.architecture.mvc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangs.architecture.dao.UserDao;
import com.yangs.architecture.entity.User;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author sol
 * @date 2023/10/17 10:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class  testMvc {


    testMvc(){
        System.out.println("测试");
    }

    @Resource
    UserDao userDao;

    @GetMapping("/yang")
    public String yang(){
        System.out.println("this is url yang !");
        return "yangs";
    }

    @GetMapping("/one")
    public void yuzhai(){
        System.out.println("this is url yuzhai !");
    }

    @GetMapping("/login")
    public void login(){
        System.out.println("登陆 !");
    }

    @GetMapping("/success")
    public void success(){
        System.out.println("登陆成功 !");
    }

//    @DS("${mybatis-data}")
    @GetMapping("/getUser")
    public Object getUser(){



        System.out.println(this);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        List<User> list = userDao.selectList(queryWrapper);
        System.out.println(Arrays.toString(list.toArray()));

        return list;
    }
}













