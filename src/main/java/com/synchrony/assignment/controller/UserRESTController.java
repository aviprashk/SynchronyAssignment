package com.synchrony.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.synchrony.assignment.model.User;
import com.synchrony.assignment.service.UserService;

@RestController
public class UserRESTController {

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/synchrony/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> retrieveUsers() {
		List<User> users = userService.retrieveUsers();
		return users;
	}	
	
	@RequestMapping(value = "/synchrony/users/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public User retrieveUser(@PathVariable("userId") Long userId) {
		return userService.retrieveUser(userId);
	}	

	@RequestMapping(value = "/synchrony/registration", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> registerUser(@RequestBody User user) {
		userService.registerUser(user);
		return new ResponseEntity<>("User Registered successfully", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/synchrony/upload", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> uploadImage() {
		try {
			String str = userService.getImgurContent();
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("Upload Image successfully", HttpStatus.CREATED);
	}
	
	
}
