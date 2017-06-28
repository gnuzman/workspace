package com.zzh.controllers;

import com.zzh.entitys.UsersEntity;
import com.zzh.mappers.UsersMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by house on 2017/6/28.
 */
@RestController
@CrossOrigin
@Api(tags = {"users"}, description = "users api")
public class TestController {

    @Autowired
    UsersMapper usersMapper;

    @GetMapping("/test1")
    public List<UsersEntity> getTest1() {
        return usersMapper.getUsers();
    }

    @GetMapping("/test2")
    public List<UsersEntity> getTest2() {
        return usersMapper.getUsers();
    }

    @GetMapping("/params1")
    public String params11(@RequestParam(required = false) String str) {
        return str;
    }

    @GetMapping("/params2/{id}")
    public String params2(@PathVariable String id) {
        return id;
    }

    @PostMapping("/params3")
    public String params3(@RequestBody String id) {
        return id;
    }


}
