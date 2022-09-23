package com.grkemaykac.citizenService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
/*@ComponentScan({"com.grkemaykac.citizenService.entity",
"com.grkemaykac.citizenService.dto",
"com.grkemaykac.citizenService.repository",
"com.grkemaykac.citizenService.service",
"com.grkemaykac.citizenService.controller",
"com.grkemaykac.citizenService.configuration",
"com.grkemaykac.authService",
"com.grkemaykac.authService.auth.controller",
"com.grkemaykac.authService.auth.dto",
"com.grkemaykac.authService.auth.entity",
"com.grkemaykac.authService.auth.repository",
"com.grkemaykac.authService.auth.service",
"com.grkemaykac.authService.config.jwt",
"com.grkemaykac.authService.config.security"})*/
@EnableSwagger2
public class CitizenServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(CitizenServiceMain.class, args);
    }
}