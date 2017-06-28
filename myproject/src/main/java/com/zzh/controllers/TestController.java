package com.zzh.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * Created by house on 2017/6/28.
 */
@RestController
@CrossOrigin
@Api(tags = {"test"}, description = "test api")
public class TestController {

    // 四种参数传入
    // 1.@RequestParam;
    // 2.@PathVariable;
    // 3.@RequestHeader;
    // 4.@RequestBody; <- 必需为post
    @GetMapping("/RequestParam")
    public String RequestParam(@RequestParam(required = false) String str) {
        return str;
    }

    @GetMapping("/PathVariable/{id}")
    public String PathVariable(@PathVariable String id) {
        return id;
    }

    @GetMapping("/RequestHeader")
    public String RequestHeader(@RequestHeader(value = "params", required = false) String params) {
        return params;
    }

    @PostMapping("/RequestBody")
    public String RequestBody(@RequestBody String id) {
        return id;
    }

}
