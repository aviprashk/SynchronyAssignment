package com.synchrony.assignment.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.synchrony.assignment.model.User;

/**
 * @Description: UserService is an Interface which defines all required abstract
 *               methods for Implementation. Also it contains the IMGUR
 *               integration URL and created Account Client_Id under IMGUR
 *               portal.
 * 
 * @author Aviprash
 *
 */
public interface UserService {
	/**
	 * IMGUR REST URL
	 */
	static final String IMGUR_IMAGE_URL = "https://api.imgur.com/3/image";

	/**
	 * CLIENT ID generated after creating Account under IMGUR portal. This
	 * client id is use to authorization to perform CRUD operations on the
	 * portal.
	 */
	static final String IMGUR_CLIENT_ID = "152135133aeb077";

	/**
	 * User should provide all require details to registered and the details
	 * persist onto H2 DB. It takes User object as Parameter
	 * 
	 * @param user
	 * @return true/false
	 */
	boolean registerUser(User user);

	/**
	 * Persistence of all user images under UserImages table in H2 DB. It takes
	 * image file as parameter and save the image in byte[] format.
	 * 
	 * @param image
	 */
	void persistUserImages(MultipartFile image);

	/**
	 * This method call retrieves the User details of specific user. It takes User id as
	 * parameter and find the record in H2 DB and returns to the client.
	 * 
	 * @param id
	 * @return User
	 */
	User retrieveUser(Long id);

	/**
	 * This method call retrieves all User details from DB.
	 * 
	 * @return List of Users.
	 */
	List<User> retrieveUsers();

	/**
	 * Checks whether User is exists in Database having required Upload image
	 * file permissions. It takes User object as Parameter and query to Database
	 * to find user existence for upload permission.
	 * 
	 * @param user
	 * @return true / false
	 */
	boolean isUserExistsWithUploadPermission(User user);

	/**
	 * Checks whether User is exists in Database having required View image file
	 * permissions. It takes User object as Parameter and query to Database to
	 * find user existence for View permission.
	 * 
	 * @param user
	 * @return true / false
	 */
	boolean isUserExistsWithViewPermission(User user);

	/**
	 * Checks whether User is exists in Database having required Delete image
	 * file permissions. It takes User object as Parameter and query to Database
	 * to find user existence for Delete permission.
	 * 
	 * @param user
	 * @return true / false
	 */
	boolean isUserExistsWithDeletePermission(User user);

	/**
	 * It retrieve the User basic information and the uploaded Images from
	 * Database. User details from User table and User Images from UserImages
	 * table. It takes the User Id as Input to Query to database to get the
	 * details.
	 * 
	 * @param id
	 * @return User Object.
	 */
	User viewUserAndImagesDetails(Long id);

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
	String uploadImage(MultipartFile image);

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
	String deleteImage(User user, String deleteHash);
}
