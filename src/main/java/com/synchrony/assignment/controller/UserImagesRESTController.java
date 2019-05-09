package com.synchrony.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.assignment.exception.SynchronyCustomException;
import com.synchrony.assignment.model.User;
import com.synchrony.assignment.service.UserService;

/**
 * @Description: This class is a REST Controller defines all end points as
 *               mention below. 1. User Registration with user credentials and
 *               basic information. 2. Associate the list of images uploaded by
 *               user in UserImages table in H@ DB. 3. Upload, View and Delete
 *               image to Imgur as well as in H2 database having One-To-Many
 *               relationship. 4. Autowired UserService which defines REST
 *               services.
 * 
 * @author Aviprash
 *
 */
@RestController
public class UserImagesRESTController {

	/**
	 * UserService for REST Controller
	 */
	@Autowired
	private UserService userService;

	/**
	 * Setter for UserService
	 * 
	 * @param userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * User registration end point. This end point takes user details and
	 * persist into H2 database. After successfully persisted into DB, it sends
	 * Acknowledgement to user. Registration end point invoke POST request
	 * method call.
	 * 
	 * @return ResponseEntity Ack message.
	 */
	@RequestMapping(value = "/synchrony/registration", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> registerUser(@RequestBody User user) {
		boolean success = userService.registerUser(user);
		if(!success)
			throw new SynchronyCustomException("User has already Registered!");
		return new ResponseEntity<>("User Registered successfully", HttpStatus.CREATED);
	}

	/**
	 * End point to retrieve all Users details from Database.
	 * 
	 * @return List of Users
	 */
	@RequestMapping(value = "/synchrony/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> retrieveUsers() {
		List<User> users = userService.retrieveUsers();
		return users;
	}

	/**
	 * End point to retrieve a user information for specific User.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/synchrony/users/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public User retrieveUser(@PathVariable("userId") Long userId) {
		return userService.retrieveUser(userId);
	}

	/**
	 * End point to view User and their uploaded images details.
	 * 
	 * @param userId
	 * @return User
	 */
	@RequestMapping(value = "/synchrony/viewuserimages/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public User viewUserAndImagesDetails(@PathVariable("userId") Long userId) {
		return userService.viewUserAndImagesDetails(userId);
	}

	/**
	 * End point to upload image to IMGUR portal. Its browse the image file to
	 * upload.
	 * 
	 * @param user
	 * @param file
	 * @return response as Acknowledgement.
	 */
	@RequestMapping(value = "/synchrony/upload", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> uploadImage(@RequestBody User user, @RequestParam("file") MultipartFile file) {
		if (userService.isUserExistsWithUploadPermission(user)) {
			String response = userService.uploadImage(file);
			userService.persistUserImages(file);
			return new ResponseEntity<>("Upload Image successfully \n" + response, HttpStatus.CREATED);
		} else {
			throw new SynchronyCustomException("User is not authorizes to upload Image to Imgur!");
		}
	}

	/**
	 * End point to delete uploaded image on IMGUR. It deletes the Image file as
	 * per generated delete hash. Delete hash generates while uploading the
	 * Image.
	 * 
	 * @param user
	 * @param deletehash
	 * @return response as Ack
	 */
	@RequestMapping(value = "/synchrony/delete/{deletehash}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Object> deleteImage(@RequestBody User user, @PathVariable("deletehash") String deletehash) {
		if (userService.isUserExistsWithDeletePermission(user)) {
			userService.deleteImage(user, deletehash);
			return new ResponseEntity<>("Image Deleted successfully", HttpStatus.CREATED);
		} else {
			throw new SynchronyCustomException("User is not authorizes to delete Image from Imgur!");
		}
	}
}
