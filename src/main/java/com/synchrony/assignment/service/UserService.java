package com.synchrony.assignment.service;

import java.util.List;

import com.synchrony.assignment.model.User;

public interface UserService {
	public void registerUser(User user);
	public User retrieveUser(Long id);
	public List<User> retrieveUsers();
	public void uploadImage();
	public String getImgurContent()  throws Exception;
	public void deleteImage(User user);
	
}
