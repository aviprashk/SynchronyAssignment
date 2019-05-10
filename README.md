# SynchronyCodingAssignment

This project develop using Maven Tool. 

# Compiled Steps

* Make sure Java home setup.
* Check Maven installed on the System.
* Download the project on specific directory.
* GoTo the project download directory.
* On the root of pom.xml file execute maven command
      maven package SynchronyAssignment
* Service should be start using command.
      java -jar SynchronyAssignment.jar	       

# H2 Database

* H2 DB Configuration

spring.datasource.url=jdbc:h2:mem:testdb  
spring.datasource.driverClassName=org.h2.Driver  
spring.datasource.username=sa  
spring.datasource.password=  
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update

* H2 Console URL

  http://localhost:8080/h2-console/
  

# REST APIs

* User Registration POST call

  http://localhost:8080/synchrony/registration
  
* View Specific User details GET call 

  http://localhost:8080/synchrony/viewuserimages/{userId}
  
* Fetch All users GET call

  http://localhost:8080/synchrony/users 

* Upload Image POST call

  http://localhost:8080/synchrony/upload
 
* Delete Image from IMGUR and Dabase based on delete Hash generated while Image upload DELETE call

  http://localhost:8080/synchrony/delete/{deletehash}
  
# Note: {localhost} use for Testing using Postman.
  
# Assumptions

* User basic information contains username, password, email, gender, permissions.
* User details persist into USER table and User Images into USERIMAGES in H2 database.
* Upload, View and Delete events require appropriate Permissions.
* Deletehash field values recorded as its require to delete image from IMGUR. This value generated while Upload even on IMGUR
* Permissions validated from User table in Database.

# Improvements

* Junit test cases written for RestController will not execute on Jenkins. All test cases uses localhost to execute REST APIs. Come up with the solution to Run REST API test cases using Jenkins.
* Write few more Junit Test cases for better code coverage. 
* Exclude un-necessary dependencies included by Spring Boot from Uber jar to minimize the JAR file size.
* Introduce Spring Transaction Management to make code more configurable.
 
# Technologies Used

* Java 8
* Spring Boot
* REST API using Spring Boot
* Maven Tool
* Mockito Framework for Junit Test cases
* IMGUR REST API
 
