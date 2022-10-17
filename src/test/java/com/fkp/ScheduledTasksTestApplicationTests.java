package com.fkp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootTest
class ScheduledTasksTestApplicationTests {

    @Test
    @Scheduled(cron = "3 * * * * ?")
    void contextLoads() {
        log.info("scheduled task run....");
        log.info(Thread.currentThread());
    }

}
