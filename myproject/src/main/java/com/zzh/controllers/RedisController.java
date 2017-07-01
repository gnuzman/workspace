package com.zzh.controllers;

import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

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
        System.out.println("服务正在运行: " + jedis.ping());

        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
        jedis.close();
    }

    @PostMapping("/redis/list")
    public List<String> redis(@RequestBody List<Integer> lst) {

        for (Integer data : lst) {
            jedis.lpush("lst", data.toString());
        }
        return jedis.lrange("lst", 0, -1);
    }
}
