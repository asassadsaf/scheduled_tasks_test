package com.fkp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    private final Object object = new Object();

    private final Lock lock = new ReentrantLock();

    @Resource(name = "myAsync")
    private ThreadPoolTaskExecutor executor;

    @RequestMapping(value = "/method1", method = RequestMethod.GET)
    public synchronized String method1(){
        System.out.println("method1 wait 10s start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method1 wait 10s end...");
        return "method1 success";
    }

    @RequestMapping(value = "/method2", method = RequestMethod.GET)
    public String method2() {
        synchronized (object){
            System.out.println("method1 wait 5s start...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method1 wait 5s end...");

            return "method2 success";
        }
    }

    @RequestMapping(value = "/method3", method = RequestMethod.GET)
    public String method3(){
        boolean b = lock.tryLock();
        if(b){
            try {
                System.out.println("method3 wait 10s start...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("method3 wait 10s end...");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            return "method3 success";
        }else {
            return "method3 no lock...";
        }
    }

    @RequestMapping(value = "/method4", method = RequestMethod.GET)
    public String method4(){
        Future<String> submit = executor.submit(() -> method3());
        String s = null;
        try {
            s = submit.get(1, TimeUnit.SECONDS);
        } catch (Exception e){

        }
        System.out.println(s);
        return s;
    }
}
