package com.example.multisource.config.quartz;

import com.example.multisource.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SpringScheduled {

    @Autowired
    private StudentService studentService;

    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    /*@Async
    @Scheduled(cron="0 0/2 * * * ?")
    public void scheduled1()
    {
        System.out.println(Thread.currentThread().getName()+":"+sdf.format(new Date()));
        studentService.update(25);
    }

    @Async
    @Scheduled(cron="0 0/3 * * * ?")
    public void scheduled2()
    {
        System.out.println(Thread.currentThread().getName()+":"+sdf.format(new Date()));
        studentService.update(18);
    }*/
}
