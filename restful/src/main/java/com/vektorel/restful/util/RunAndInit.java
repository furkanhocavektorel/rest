package com.vektorel.restful.util;

import com.vektorel.restful.service.OwnerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunAndInit {

    @PostConstruct
    public void run(){
        new Thread(()->{
            System.err.println("11");
        }).start();
        new Thread(()->{
            System.err.println("12");
        }).start();

    }
}
