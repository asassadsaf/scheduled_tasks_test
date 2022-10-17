package com.fkp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class ScheduledTasksTestApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ScheduledTasksTestApplication.class, args);
        log.info("main....            " + Thread.currentThread().getName());
    }

}
