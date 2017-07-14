package com.zzh.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by house on 2017/7/14.
 */
@RestController
@CrossOrigin
public class ZabbixController {
    private RestTemplate restTemplate;
    @GetMapping("/zabbix-login")
    public String login() {
//        HttpEntity<String> entity = new HttpEntity<String>("", "");
        //
        return "";
    }
}
