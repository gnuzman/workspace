package com.zzh.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Api(tags = "java")
public class JavaController {
 @GetMapping("java/currenttime")
    public String getCurrentTime() {
     return "" + System.currentTimeMillis();
 }
}
