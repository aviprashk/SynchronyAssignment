package com.synchrony.assignment.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Description: This class is UserImages model class or entity. Using this class the
 *               USERIMAGES	table created in H2 database. All image related information
 *               including the image in byte[] persisted in database.
 * 
 *               Its having mapping column User Id to maintain one To Many relationship 
 *               with User table.  
 *               
 * @author Aviprash
 *
 */
@Entity
@Table(name = "USERIMAGE")
public class UserImages {

	/*
	 * Image Id field. Auto generated identity column values in UserImages table.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mid;

	/*
	 * Image field. It receives the image and persist into byte[] format in database.
	 */
	@Lob
	@Column(name = "USER_IMAGE", nullable = true, columnDefinition = "mediumblob")
	private byte[] image;

	/*
	 * ImageImgurHash field. This filed data persist into database. Hash generated while uploading 
	 * the image.
	 */
	private String imageImgurHash;
	
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

	/**
	 * @return the mid
	 */
	public Long getMid() {
		return mid;
	}

	/**
	 * @param mid the mid to set
	 */
	public void setMid(Long mid) {
		this.mid = mid;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @return the imageImgurHash
	 */
	public String getImageImgurHash() {
		return imageImgurHash;
	}

	/**
	 * @param imageImgurHash the imageImgurHash to set
	 */
	public void setImageImgurHash(String imageImgurHash) {
		this.imageImgurHash = imageImgurHash;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserImages [mid=" + mid + ", image=" + Arrays.toString(image) + ", imageImgurHash=" + imageImgurHash
				+ ", user=" + user + "]";
	}
}
