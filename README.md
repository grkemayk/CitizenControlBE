# CitizenControlBE
Citizen control and monitoring system.(Spring Cloud + Spring Boot + Spring Security)

I created this project with Spring Boot, Spring Cloud and Spring Security.

I used:
1) Spring Security Fot Auth.
2) Spring Cloud Gateway for Gateway.
3) Spring Cloud Eureka Server for load balancing and microservices communications.
4) Spring Boot Admin server for monitoring(It's couldn't work clearly, I think swagger and actuator doesn't work together.)
5) Spring Cloud Zipkin for network logs.
6) Swagger for documentation.
7) I can find project artch. and its flow in base directory.

You need a Postgre database 5432 port, and its name should be ''D14-AI. You can set it in config server.
You can find module's configuration files from configServer -> src/main/java/resource/microservices

If you want to run project auth, firstly you should open auth dependency to citizenService -> pom.xml -> authService dependency
secondly you should open citizenService -> CitizenServiceMain @ComponentScan comment line.
After the run all modules you have to register and login for auth. then you need to Bearer token

    1) Register: Auth server -> 8004 -> POST -> 8004/auth/register ->

        {
          "username": "admin",
          "email": "grkemaykac@gmail.com",
          "password": "123"
        }

    2) Token: Auth server -> 8004 -> POST -> 8004/auth/login
        {
            "username":"admin",
            "password":"123"
    }

It return a token. You should add Auth. header and its value = "Bearer " + Token
Now you can access all end-point from postman or your UI.

Project's missing features:
1) Filter can be dynamic.
2) Dto models can be generic for every end-points



