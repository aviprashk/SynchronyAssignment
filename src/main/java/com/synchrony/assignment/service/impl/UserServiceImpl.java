package com.synchrony.assignment.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.assignment.model.User;
import com.synchrony.assignment.model.UserImages;
import com.synchrony.assignment.repository.UserImagesRepository;
import com.synchrony.assignment.repository.UserRepository;
import com.synchrony.assignment.service.UserService;
import com.synchrony.assignment.util.UserUtil;

/**
 * @Description: This is a UserService implementation class. It access the JPA
 *               UserRepository API's to perform the CRUD operation on User and
 *               the Images uploaded or deleted in H2 DB. The Template or
 *               skeleton defined in the UserService Interface. Any further
 *               extension we can directly add Java 8 supported default method
 *               in UserService Interface which avoid the impact on this
 *               UserService Implementation.
 * 
 * @author Aviprash
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private Lock lock;

	/**
	 * Autowired UserRepository to perform CRUD operation on User table.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Autowired UserImagesRepository to perform CRUD operation on UserImages
	 * table.
	 */
	@Autowired
	private UserImagesRepository userImagesRepository;

	/**
	 * Autowired UserUtil to performs Utility functionality.
	 */
	@Autowired
	private UserUtil userUtil;

	/**
	 * Setter Injection for UserRepository
	 * 
	 * @param userRepository
	 */
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Setter Injection for UserImagesRepository
	 * 
	 * @param userImagesRepository
	 */
	public void setUserImagesRepository(UserImagesRepository userImagesRepository) {
		this.userImagesRepository = userImagesRepository;
	}

	/**
	 * Registration user service method call. This method check the duplicate
	 * user entry in H@ DB. If the User is already exists then it returns false
	 * else returns true for successful registration.
	 * 
	 * @param User
	 */
	@Override
	public boolean registerUser(User user) {
		lock = new ReentrantLock();
		try {
			lock.lock();
			ExampleMatcher nameMatcher = ExampleMatcher.matching().withMatcher("username",
					GenericPropertyMatchers.caseSensitive());
			Example<User> userExample = Example.of(user, nameMatcher);
			boolean exists = userRepository.exists(userExample);
			if (!exists) {
				String password = userUtil.decryptPassword(user);
				if (!password.isEmpty())
					user.setPassword(password);
				userRepository.save(user);
				return true;
			}
		} finally {
			lock.unlock();
		}
		return false;
	}

	/**
	 * Persistence of all user images under UserImages table in H2 DB. It takes
	 * image file as parameter and save the image in byte[] format.
	 * 
	 * @param image
	 */
	@Override
	public void persistUserImages(MultipartFile image) {
		UserImages images = new UserImages();
		lock = new ReentrantLock();
		try {
			lock.lock();
			images.setImage(image.getBytes());
			userImagesRepository.save(images);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

	/**
	 * This method call retrieves single User details. It takes User id as
	 * parameter and find the record in H2 DB and returns to the client.
	 * 
	 * @param id
	 * @return User
	 */
	@Override
	public User retrieveUser(Long id) {
		Optional<User> optUser = userRepository.findById(id);
		return optUser.get();
	}

	/**
	 * This method call retrieves all User details from DB.
	 * 
	 * @return List of Users.
	 */
	@Override
	public List<User> retrieveUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	/**
	 * It retrieve the User basic information and the uploaded Images from
	 * Database. User details from User table and User Images from UserImages
	 * table. It takes the User Id as Input to Query to database to get the
	 * details.
	 * 
	 * @param id
	 * @return User Object.
	 */
	@Override
	public User viewUserAndImagesDetails(Long id) {
		Optional<User> optUser = userRepository.findById(id);
		return optUser.get();
	}

	/**
	 * Checks whether User is exists in Database having required Upload image
	 * file permissions. It takes User object as Parameter and query to Database
	 * to find user existence for upload permission.
	 * 
	 * @param user
	 * @return true / false
	 */
	@Override
	public boolean isUserExistsWithUploadPermission(User user) {
		String uploadPerms = "";
		ExampleMatcher NAME_MATCHER = ExampleMatcher.matching().withMatcher("username",
				GenericPropertyMatchers.caseSensitive());
		Example<User> userExample = Example.of(user, NAME_MATCHER);
		boolean exists = userRepository.exists(userExample);
		if (exists)
			uploadPerms = user.getPermission();
		return (!uploadPerms.isEmpty() && uploadPerms.equals("Upload") ? true : false);
	}

	/**
	 * Checks whether User is exists in Database having required View image file
	 * permissions. It takes User object as Parameter and query to Database to
	 * find user existence for View permission.
	 * 
	 * @param user
	 * @return true / false
	 */
	@Override
	public boolean isUserExistsWithViewPermission(User user) {
		String uploadPerms = "";
		ExampleMatcher NAME_MATCHER = ExampleMatcher.matching().withMatcher("username",
				GenericPropertyMatchers.caseSensitive());
		Example<User> userExample = Example.of(user, NAME_MATCHER);
		boolean exists = userRepository.exists(userExample);
		if (exists)
			uploadPerms = user.getPermission();
		return (!uploadPerms.isEmpty() && uploadPerms.equals("View") ? true : false);
	}

	/**
	 * Checks whether User is exists in Database having required Delete image
	 * file permissions. It takes User object as Parameter and query to Database
	 * to find user existence for Delete permission.
	 * 
	 * @param user
	 * @return true / false
	 */
	@Override
	public boolean isUserExistsWithDeletePermission(User user) {
		String deletePerms = "";
		ExampleMatcher NAME_MATCHER = ExampleMatcher.matching().withMatcher("username",
				GenericPropertyMatchers.caseSensitive());
		Example<User> userExample = Example.of(user, NAME_MATCHER);
		boolean exists = userRepository.exists(userExample);
		if (exists)
			deletePerms = user.getPermission();
		return (!deletePerms.isEmpty() && deletePerms.equals("Delete") ? true : false);
	}

	/**
	 * This API method call uploads the image to IMGUR portal. It takes Image
	 * file as Input parameter. Before uploading the image file, it first checks
	 * the Proper Authentication using CLIENT ID generated from IMGUR portal.
	 * 
	 * After successfully Authentication and check the required upload
	 * permission to database thie it upload the Image on IMGUR, it also
	 * inserted record in UserImages table. One user can upload multiple images.
	 * User and UserImages are One To Many relationship.
	 * 
	 * @param image
	 *            file
	 * @return String Acknowledgement.
	 */
	@Override
	public String uploadImage(MultipartFile image) {
		HttpURLConnection conn = UserUtil.getHttpConnection(IMGUR_CLIENT_ID, IMGUR_IMAGE_URL, "POST");
		UserUtil.writeToConnection(conn, "image=" + UserUtil.toBase64(UserUtil.convert(image)));
		return UserUtil.getResponse(conn);
	}

	/**
	 * This API method call deletes the image from IMGUR portal. It takes User
	 * object and delete hash as Input parameter. Before deleting the image
	 * file, it first checks the Proper Authentication using CLIENT ID generated
	 * from IMGUR portal.
	 * 
	 * After successful IMGUR Authentication and required delete permission to
	 * User from H2 DB, this it allows user to delete the image using provided
	 * deletehash string.
	 * 
	 * @param user
	 * @param deleteHash
	 * @return String Acknowledgement.
	 */
	@Override
	public String deleteImage(User user, String deleteHash) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization: Client-ID", IMGUR_CLIENT_ID);
		String resourceUrl = IMGUR_IMAGE_URL + "/" + deleteHash;
		ExampleMatcher imagehash_matcher = ExampleMatcher.matching().withMatcher("deleteHash",
				GenericPropertyMatchers.caseSensitive());
		Example<UserImages> userImageExample = Example.of(new UserImages(), imagehash_matcher);
		Optional<UserImages> images = userImagesRepository.findOne(userImageExample);
		if (images.isPresent()) {
			userImagesRepository.delete(images.get());
			restTemplate.delete(resourceUrl, headers);
		} else {
			return "Issue with Image deletion from Imgur and UserImages Table! Please investigate";
		}
		return "Image deleted successfully from Imgur and UserImages Table";
	}
}
