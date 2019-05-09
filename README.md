### SynchronyCodingAssignment

This project develop using Maven Tool. 

### Compiled Steps

# 1. Make sure Java home setup.
# 2. Check Maven installed on the System.
# 3. Download the project on specific directory.
# 4. GoTo the project download directory.
# 5. On the root of pom.xml file execute maven command
       maven package SynchronyAssignment
# 6. Service should be start using command.
	   java -jar SynchronyAssignment.jar	       

### Assumptions

# 1.User basic information contains username, password, email, gender, permissions.
# 2.User registration with user details persist into USER table in H2 database.
# 3.User Images persist into USERIMAGES table in H2 database.
# 4.Follow One To Many relationship with User and UserImages tables.
# 5.When user upload image on IMGUR portal at the same time entry should persist into USERIMAGES table.
# 6.Upload image user should have Upload Permission.       
# 7.View User details and Images, User should have View Permissions. 
# 8.Delete Image, User should have Delete Permissions.
# 9.Keep record deletehash generated while upload the image on IMGUR which help to delete image.
# 10.Permissions validated from User table in Database.
# 11.Associate all uploaded images by user persist into USERIMAGES table.   


### Improvements

# 1.Junit test cases written for RestController will not execute on Jenkins. All test cases uses localhost to execute REST APIs. Come up with the solution to Run REST API test cases using Jenkins.
# 2.Write few more Junit Test cases for better code coverage. 
# 3.Exclude un-necessary dependencies included by Spring Boot from Uber jar to minimize the JAR file size.
# 4.Introduce Spring Transaction Management to make code more configurable.
 
### Technologies Used

# 1.Java 8
# 2.Spring Boot
# 3.REST API using Spring Boot
# 4.Maven Tool
# 5.Mockito Framework for Junit Test cases
# 6.IMGUR REST API
 
