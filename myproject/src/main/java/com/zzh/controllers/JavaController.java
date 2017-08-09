package com.zzh.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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

    @GetMapping("java/thread")
    public String testThread() throws Exception {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(100);
        ScheduledFuture<?> t;
        t = schedule.scheduleAtFixedRate(new TreadTask("param-1"), 0,5, TimeUnit.SECONDS);
        Thread.sleep(10000);
        t.cancel(false);
        return "";
    }
}
