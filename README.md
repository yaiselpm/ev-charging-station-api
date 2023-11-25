# Spring Boot "Charging Station Project

This is a sample Java / Maven / Spring Boot (version 2.7.17) application that can be used as a starter for creating a charging station. I hope it helps you.

## How to Run 

This application is packaged as a jar which has Tomcat 8 embedded. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8
* You can build the project and run the tests by running ```mvn clean package```
* If you want to build and run skipping the test by running ```mvn -DskipTests package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/ev-charging-station-api-0.0.1.jar
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```

## About the Service

Here are some endpoints you can call:

### Get information about stations, availables.

```
http://localhost:8080/stations
http://localhost:8080/stations/availables
```

### Create a station resource

```
POST /stations
Accept: application/json
Content-Type: application/json

{        
    "location":{       
        "address":"Station puedfba ",
        "latitude": 13246,
        "longitude":31654
    },
    "chargerType":"AC",
    "status":"Available"
   
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/stations/stn101
```

### Retrieve a list of stations

```
http://localhost:8080/stations

Response: HTTP 200
Content: list 
```

### Update a station resource

```
PUT /stations/stn101
Accept: application/json
Content-Type: application/json

{
    "id":"stn101",    
    "location":{
         "id":1,      
        "address":"Station puedfba ",
        "latitude": 13246,
        "longitude":31654
    },
    "chargerType":"AC",
    "status":"Available"
   
}

RESPONSE: HTTP 204 (No Content)
```
# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. 

# Running the project with MySQL

This project uses a relational database such as MySQL. 

Here is what you would do to back the services with MySQL, for example:


```
        <dependency>
                <groupId>com.mysql</groupId>
                <artifactId>mysql-connector-j</artifactId>
                <version>8.0.33</version>
        </dependency>
```

### Append this to the end of application.property: 

```
---
spring.datasource.url=jdbc:mysql://localhost:3306/charging-station?createDatabaseIfNotExist=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.profiles.active=docker
```
## Docker tag 
# yaiselpmm/springboot-chargingstation

