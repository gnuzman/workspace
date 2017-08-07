package com.zzh.entities;

public class MetricDataEntity {
    private Long id;
    private String hostIp;
    private String metricName;
    private Integer metricValue;

    public MetricDataEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public Integer getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(Integer metricValue) {
        this.metricValue = metricValue;
    }
}
