package com.zzh.mappers;

import com.zzh.entities.MetricDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface MetricDataMapper {
    Long addMetricData(MetricDataEntity metricDataEntity);
}
