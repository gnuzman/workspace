package com.zzh.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Api(tags = "java")
public class JavaController {
    @GetMapping("java/currenttime")
    public String getCurrentTime() {
        return "" + System.currentTimeMillis();
    }

    @GetMapping("java/test-map")
    public String testMap() {
        Map<String, Object> test = new HashMap<>();
        test.put("a", 1);
        test.put("b", 2);
        test.put("c", 3);

        if (null == test.get("d")) {
            return "null";
        } else {
            return test.get("d").toString();
        }
    }
}
