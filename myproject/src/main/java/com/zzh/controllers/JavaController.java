package com.zzh.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("java/params")
    public String testParams(@RequestParam(value = "param1") String param1,
                             @RequestParam(value = "param2") String param2,
                             @RequestParam(value = "param3") String param3) {
        return "";
    }
}
