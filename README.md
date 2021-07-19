# Distributed-Microservice-Application

This is a repo for a Distributed Microservice Application written in Java using Spring Boot. 

The repo has basic CRUD functionality and uses an in-memory H2 database on startup to populate a Books table.

The application is made up of the following components:

• REST based microservices for US States and US State Capitals built with Spring Web and Spring JPA using in memory H2 Database
• Configuration management with Spring Cloud Config (File System backend)
• Service discovery with Spring Cloud Netflix Eureka
• API gateways with Spring Cloud Netflix Zuul
• Distributed Tracing and Logging with Spring Cloud Sleuth and Zipkin
