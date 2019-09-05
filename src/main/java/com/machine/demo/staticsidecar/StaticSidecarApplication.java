package com.machine.demo.staticsidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@EnableSidecar
@SpringBootApplication
public class StaticSidecarApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticSidecarApplication.class, args);
    }

}
