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
* User registration with user details persist into USER table in H2 database.
* User Images persist into USERIMAGES table in H2 database.
* Follow One To Many relationship with User and UserImages tables.
* When user upload image on IMGUR portal at the same time entry should persist into USERIMAGES table.
* Upload image user should have Upload Permission.       
* View User details and Images, User should have View Permissions. 
* Delete Image, User should have Delete Permissions.
* Keep record deletehash generated while upload the image on IMGUR which help to delete image.
* Permissions validated from User table in Database.
* Associate all uploaded images by user persist into USERIMAGES table.   


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
 
