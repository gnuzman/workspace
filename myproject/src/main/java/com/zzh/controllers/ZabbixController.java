package com.zzh.controllers;

import com.zzh.entities.MetricDataEntity;
import com.zzh.services.MetricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by house on 2017/7/14.
 */
@RestController
@CrossOrigin
public class ZabbixController {
    @Autowired
    private MetricDataService metricDataService;

    @GetMapping("/zabbix/metric")
    public Map<String, Object> zabbix(
            @RequestParam(value = "agentAddress", defaultValue = "192.168.42.254") String agentAddress,
            @RequestParam(value = "port", defaultValue = "10050") String agentPort,
            @RequestParam(value = "metrics", defaultValue = "proc.num[]|system.cpu.intr|agent.ping|proc.num[,,run]") String metrics) throws Exception {
        InetAddress address = InetAddress.getByName(agentAddress);
        Integer port = Integer.parseInt(agentPort);
        PassiveAgentClient zabbixClient = new PassiveAgentClient(address, port);

        List<String> lstMetric = Arrays.asList(metrics.split("\\|"));
        Map<String, Object> values = zabbixClient.getValues(lstMetric);
        values.forEach((k, v) -> {
            MetricDataEntity metricDataEntity = new MetricDataEntity();
            metricDataEntity.setHostIp(agentAddress);
            metricDataEntity.setMetricName(k.toString());
            metricDataEntity.setMetricValue(Integer.parseInt(v.toString()));
            metricDataService.addMetricData(metricDataEntity);
        });
        return values;
    }
}
