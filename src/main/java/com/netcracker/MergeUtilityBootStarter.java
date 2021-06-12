package com.netcracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
public class MergeUtilityBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(MergeUtilityBootStarter.class, args);
    }
}
