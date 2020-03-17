package com.arobs.project.configuration;

import com.arobs.project.service.impl.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
public class SchedulerConfig {

    final SchedulerService schedulerService;

    @Autowired
    public SchedulerConfig(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }
}