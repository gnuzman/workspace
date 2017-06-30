package com.zzh.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * Created by house on 2017/6/29.
 */
@RestController
@CrossOrigin
public class RedisController {

    private Jedis jedis = new Jedis("localhost");
    @GetMapping("/redis")
    public void redis() {

        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
        jedis.close();
    }
}
