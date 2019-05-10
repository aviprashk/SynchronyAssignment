package com.synchrony.assignment.controller;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.synchrony.assignment.model.User;

/**
 * @Description: Test cases for Rest Controller
 * 
 * @author Aviprash
 *
 */
@Ignore
public class UserImagesRESTControllerTest {

	RestTemplate restTemplate = null;
	HttpHeaders headers = null;

	@Before
	public void init() {
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
	}

	/**
	 * Test case to test REST API for User Registration.
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testRegisterUser() throws URISyntaxException {
		User user = new User("Aviprash", "Avi@123", "aviprashk@gmail.com", "Male", "Upload");
		final String baseUrl = "http://localhost:8080/synchrony/registration";
		URI uri = new URI(baseUrl);
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		assertEquals("User successfully Registered!", response.getBody());
	}

	/**
	 * @Description: Test case to test REST API for RetrieveUser
	 * @throws URISyntaxException
	 */
	@Test
	public void testRetrieveUser() throws URISyntaxException {
		final String baseUrl = "http://localhost:8080/synchrony/users/123";
		URI uri = new URI(baseUrl);
		ResponseEntity<User> result = restTemplate.getForEntity(uri, User.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, result.getBody().getId());
	}

	/**
	 * @Description: Test case to test REST API for ViewUserAndImagesDetails
	 * @throws URISyntaxException
	 */
	@Test
	public void testViewUserAndImagesDetails() throws URISyntaxException {
		final String baseUrl = "http://localhost:8080/synchrony/viewuserimages/123";
		URI uri = new URI(baseUrl);
		ResponseEntity<User> result = restTemplate.getForEntity(uri, User.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, result.getBody().getId());
	}
}
