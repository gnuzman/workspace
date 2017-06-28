package com.zzh.controllers;

import com.zzh.entitys.UsersEntity;
import com.zzh.mappers.UsersMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getUser")
    public List<UsersEntity> getUser() {
        return usersMapper.getUsers();
    }

    @GetMapping("/getUser/{id}")
    public List<UsersEntity> getUser(@PathVariable Integer id) {
        return usersMapper.getUsers();
    }
}
