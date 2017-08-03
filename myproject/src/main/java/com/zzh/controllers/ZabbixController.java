package com.zzh.controllers;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by house on 2017/7/14.
 */
@RestController
@CrossOrigin
public class ZabbixController {

    @GetMapping("/zabbix/metric")
    public Map<String, Object> zabbix(
            @RequestParam(value = "agentAddress", defaultValue = "192.168.42.84") String agentAddress,
            @RequestParam(value = "port", defaultValue = "10050") String agentPort,
            @RequestParam(value = "metrics", defaultValue = "proc.num[]|system.cpu.intr|agent.ping|proc.num[,,run]") String metrics) throws Exception {
        InetAddress address = InetAddress.getByName(agentAddress);
        Integer port = Integer.parseInt(agentPort);
        PassiveAgentClient zabbixClient = new PassiveAgentClient(address, port);

        List<String> lstMetric = Arrays.asList(metrics.split("\\|"));
        Map<String, Object> values = zabbixClient.getValues(lstMetric);
        return values;
    }
}
