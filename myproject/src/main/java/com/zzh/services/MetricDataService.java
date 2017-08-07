package com.zzh.services;

import com.zzh.entities.MetricDataEntity;
import com.zzh.mappers.MetricDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MetricDataService {
    @Autowired
    private MetricDataMapper metricDataMapper;
    public Long addMetricData(MetricDataEntity metricDataEntity) {
        return metricDataMapper.addMetricData(metricDataEntity);
    }
}
