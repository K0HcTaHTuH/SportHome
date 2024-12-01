package com.dolgoborodovkv.sporthome;

import org.springframework.boot.SpringApplication;

public class TestSportHomeApplication {

    public static void main(String[] args) {
        SpringApplication.from(SportHomeApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
