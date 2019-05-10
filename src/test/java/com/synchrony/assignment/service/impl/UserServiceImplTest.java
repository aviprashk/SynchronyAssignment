/**
 * 
 */
package com.synchrony.assignment.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import com.synchrony.assignment.model.User;
import com.synchrony.assignment.repository.UserImagesRepository;
import com.synchrony.assignment.repository.UserRepository;
import com.synchrony.assignment.util.UserUtil;

/**
 * Test cases for UserServiceImpl using Mockito Framework.
 * 
 * @author Aviprash
 *
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserImagesRepository userImagesRepository;

	@Mock
	UserUtil userUtil;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userServiceImpl = new UserServiceImpl();
	}

	/**
	 * Test case to Register User with user basic details.
	 */
	@Test
	public void testRegisterUser() {
		@SuppressWarnings("unchecked")
		Example<User> example = Mockito.mock(Example.class);
		when(userRepository.exists(example)).thenReturn(false);
		User user = new User("Aviprash", "Avi@123", "aviprashk@gmail.com", "Male", "Upload");
		boolean successFlag = userServiceImpl.registerUser(user);
		assertTrue(successFlag);
	}

	/**
	 * Test case to retrieve User details for specific user id.
	 */
	@Test
	public void testRetrieveUser() {
		Long id = 123L;
		User user = new User("Ganesh", "Ganesh@123", "ganesh@gmail.com", "Male", "Upload");
		Optional<User> userOptional = Optional.ofNullable(user);
		when(userRepository.findById(id)).thenReturn(userOptional);
		User userRet = userServiceImpl.retrieveUser(id);
		assertNotNull(userRet);
	}

	/**
	 * Test case to retrieve All User details.
	 */
	@Test
	public void testRetrieveAllUsers(){
		User user = new User("Ganesh", "Ganesh@123", "ganesh@gmail.com", "Male", "Upload");
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(userRepository.findAll()).thenReturn(userList);
		List<User> userRet = userServiceImpl.retrieveUsers();
		assertNotNull(userRet);
	}
	
	/**
	 * Test case to view User and Images details for specific user.
	 */
	@Test
	public void testViewUserAndImagesDetails(){
		Long id = 123L;
		User user = new User("Ganesh", "Ganesh@123", "ganesh@gmail.com", "Male", "Upload");
		Optional<User> userOptional = Optional.ofNullable(user);
		when(userRepository.findById(id)).thenReturn(userOptional);
		User userRet = userServiceImpl.viewUserAndImagesDetails(id);
		assertNotNull(userRet);		
	}
	
	/**
	 * Test case to verify User has upload permissions.
	 */
	@Test
	public void testIsUserExistsWithUploadPermission() {
		@SuppressWarnings("unchecked")
		Example<User> example = Mockito.mock(Example.class);
		when(userRepository.exists(example)).thenReturn(false);
		User user = new User("Aviprash", "Avi@123", "aviprashk@gmail.com", "Male", "Upload");
		boolean successFlag = userServiceImpl.isUserExistsWithUploadPermission(user);
		assertTrue(successFlag);
	}
	
	/**
	 * Test case to verify User has View permissions.
	 */
	@Test
	public void testIsUserExistsWithViewPermission() {
		@SuppressWarnings("unchecked")
		Example<User> example = Mockito.mock(Example.class);
		when(userRepository.exists(example)).thenReturn(false);
		User user = new User("Aviprash", "Avi@123", "aviprashk@gmail.com", "Male", "View");
		boolean successFlag = userServiceImpl.isUserExistsWithViewPermission(user);
		assertTrue(successFlag);
	}
	
	/**
	 * Test case to verify User has Delete permissions.
	 */
	@Test
	public void testIsUserExistsWithDeletePermission() {
		@SuppressWarnings("unchecked")
		Example<User> example = Mockito.mock(Example.class);
		when(userRepository.exists(example)).thenReturn(false);
		User user = new User("Aviprash", "Avi@123", "aviprashk@gmail.com", "Male", "Delete");
		boolean successFlag = userServiceImpl.isUserExistsWithDeletePermission(user);
		assertTrue(successFlag);
	}
}

