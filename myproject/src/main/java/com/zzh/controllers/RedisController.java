package com.zzh.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by house on 2017/6/29.
 */
@RestController
@CrossOrigin
@Api(tags = "redis", description = "redis api")
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

    @PostMapping("/redis/list/{key}")
    public List<String> add(
            @PathVariable String key,
            @RequestBody List<Integer> lst) {

        for (Integer data : lst) {
            jedis.rpush(key, data.toString());
        }
        return jedis.lrange(key, 0, -1);
    }

    @DeleteMapping("/redis/list/{key}")
    public List<String> deleteList(@PathVariable String key) {
        jedis.del(key);
        return jedis.lrange(key, 0, -1);
    }
}
