package com.zzh.controllers;

import com.zzh.entities.UsersEntity;
import com.zzh.mappers.UsersMapper;
import com.zzh.services.UsersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by house on 2017/6/28.
 */
@RestController
@CrossOrigin
@Api(tags = {"users"}, description = "users api")
public class UsersController {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersService usersService;

    @GetMapping("/getUsers")
    public List<UsersEntity> getUsers(@RequestParam(required = false) String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return usersMapper.getUsers(params);
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
