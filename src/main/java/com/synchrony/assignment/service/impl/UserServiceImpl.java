package com.synchrony.assignment.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.synchrony.assignment.model.User;
import com.synchrony.assignment.repository.UserRepository;
import com.synchrony.assignment.service.UserService;

@SuppressWarnings("restriction")
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void registerUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User retrieveUser(Long id) {
		Optional<User> optUser = userRepository.findById(id);
		return optUser.get();
	}

	@Override
	public List<User> retrieveUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	   public String getImgurContent() throws Exception {

	         String clientID = "152135133aeb077";

	    URL url;

	    url = new URL("https://api.imgur.com/3/image");
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	    String data = URLEncoder.encode("image", "UTF-8") + "="
	            + URLEncoder.encode("http://i.imgur.com/FB9OZWQ.jpg", "UTF-8");
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "Client-ID " + clientID);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type",
	            "application/x-www-form-urlencoded");

	    conn.connect();
	    StringBuilder stb = new StringBuilder();
	    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	    wr.write(data);
	    wr.flush();

	    // Get the response
	    BufferedReader rd = new BufferedReader(
	            new InputStreamReader(conn.getInputStream()));
	    String line;
	    while ((line = rd.readLine()) != null) {
	        stb.append(line).append("\n");
	    }
	    wr.close();
	    rd.close();

	    System.out.println(stb.toString());

	    return stb.toString();
	}
	
	
	
	@SuppressWarnings("restriction")
	@Override
	public void uploadImage() {
		String API_KEY = "152135133aeb077";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedImage image = null;
		File file = new File("D:\\Aviprash BackUp\\wallpapers\\ganesh on banna leaf.jpg");
		URL url = null;
		try {

			image = ImageIO.read(file);
			ImageIO.write(image, "jpg", baos);

			url = new URL("https://api.imgur.com/3/image");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// encodes picture with Base64 and inserts api key
		String data = "";
		try {
			data = URLEncoder.encode("image", "UTF-8") + "="
					+ URLEncoder.encode(Base64.encode(baos.toByteArray()), "UTF-8");
			data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(API_KEY, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// opens connection and sends data
		URLConnection conn = null;
		try {
			conn = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		conn.setDoOutput(true);
		OutputStreamWriter wr = null;
		try {
			wr = new OutputStreamWriter(conn.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			wr.write(data);
			wr.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteImage(User user) {

	}
}
