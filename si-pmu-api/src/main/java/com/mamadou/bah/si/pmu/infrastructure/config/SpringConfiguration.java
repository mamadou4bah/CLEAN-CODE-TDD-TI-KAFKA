package com.mamadou.bah.si.pmu.infrastructure.config;


import com.mamadou.bah.si.pmu.domain.common.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {"com.mamadou.bah.si.pmu.application"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class})})
public class SpringConfiguration {
}
