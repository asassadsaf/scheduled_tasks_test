package com.fkp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(value = 1)
@Slf4j
public class MyApplicationRunner2 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("MyApplicationRunner2 wait start....");
        Thread.sleep(3000);
        log.info("MyApplicationRunner2 wait end....");
        log.info("MyApplicationRunner2....        " + Thread.currentThread().getName());
    }
}
