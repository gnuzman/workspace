package com.zzh.controllers;

import com.zzh.entities.UsersEntity;
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
public class UsersController {
    @Autowired
    private UsersMapper usersMapper;

    @GetMapping("/getUsers")
    public List<UsersEntity> getUsers() {
        return usersMapper.getUsers();
    }

    @GetMapping("/getUser/{id}")
    public UsersEntity getUser(@PathVariable Integer id) {
        return usersMapper.getUser(id);
    }

    // 传入参数直接带回，插入值的ID
    // xml配置加：useGeneratedKeys="true" keyProperty="id"
    @PostMapping("/addUser")
    public UsersEntity AddUser(@RequestBody UsersEntity usersEntity) {
        usersMapper.addUser(usersEntity);
        return usersEntity;
    }
}
