# Distributed-Microservice-Application

This is a repo for a Distributed Microservice Application written in Java using Spring Boot. 

The application returns information relating to US States and US State Capitals.

It is made up of the following components:

• REST based microservices built with **Spring Web** and **Spring JPA**<br />
• Data populated on start-up using an in-memory **H2 Database** <br />
• Configuration management with **Spring Cloud Config** (File System backend) <br />
• Service discovery with **Spring Cloud Netflix Eureka** <br />
• API gateways with **Spring Cloud Netflix Zuul** <br />
• Distributed Tracing and Logging with **Spring Cloud Sleuth** and **Zipkin** <br />
• Each service includes **Derived Queries** using a **JPARepository** <br />
• The endpoints of the application are documented using **Swagger**

