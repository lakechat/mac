package com.spring.scheduler;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SpringScheduleFixedRateExample {
    
    private AtomicInteger counter = new AtomicInteger(0);
    
    @Scheduled(fixedRate = 2000)
    public void fixedRateJob() {
        int jobId = counter.incrementAndGet();
        System.out.println("Job @ fixed rate " + new Date() + ", jobId: " + jobId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Job " + jobId + " done");
    }
 
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringScheduleFixedRateExample.class);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }

}
