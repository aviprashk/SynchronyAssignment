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

# REST APIs

* User Registration POST call

  /synchrony/registration
  
* View Specific User details GET call 

  /synchrony/viewuserimages/{userId}
  
* Fetch All users GET call

  /synchrony/users 

* Upload Image POST call

  /synchrony/upload
 
* Delete Image from IMGUR and Dabase based on delete Hash generated while Image upload DELETE call

  /synchrony/delete/{deletehash}
  
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
 
