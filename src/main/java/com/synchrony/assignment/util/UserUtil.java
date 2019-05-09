package com.synchrony.assignment.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.assignment.exception.WebException;
import com.synchrony.assignment.model.StatusCode;
import com.synchrony.assignment.model.User;

/**
 * @Description: This is a Utility class. Having all required functions to use
 *               in Application.
 * 
 * @author Aviprash
 *
 */
@Component
public class UserUtil {

	/**
	 * This method call decrypt the password enter by User and persist the
	 * decrypted password into DB.
	 * 
	 * @param user
	 * @return
	 */
	public String decryptPassword(User user) {
		String decryptPwrd = "";
		try {
			MessageDigest alg = MessageDigest.getInstance("MD5");
			String password = user.getPassword();
			alg.reset();
			alg.update(password.getBytes());
			byte[] msgDigest = alg.digest();
			BigInteger number = new BigInteger(1, msgDigest);
			decryptPwrd = number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return decryptPwrd;
	}

	/**
	 * This method converts the MultipartFile to File object.
	 * 
	 * @param file
	 * @return File object.
	 */
	public static File convert(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convFile;
	}

	/**
	 * This method encodes the file object to String using Base64 method api.
	 * 
	 * @param file
	 * @return
	 */
	public static String toBase64(File file) {
		try {
			byte[] b = new byte[(int) file.length()];
			FileInputStream fs = new FileInputStream(file);
			fs.read(b);
			fs.close();
			return URLEncoder.encode(DatatypeConverter.printBase64Binary(b), "UTF-8");
		} catch (IOException e) {
			throw new WebException(StatusCode.UNKNOWN_ERROR, e);
		}
	}

	/**
	 * Creates and sets up an HttpURLConnection for use with the Imgur API.
	 * 
	 * @param url
	 *            The URL to connect to. (check Imgur API for correct URL).
	 * @return The newly created HttpURLConnection.
	 */
	public static HttpURLConnection getHttpConnection(String ClientId, String url, String requestMethod) {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("Authorization", "Client-ID " + ClientId);
			conn.setReadTimeout(100000);
			conn.connect();
			return conn;
		} catch (UnknownHostException e) {
			throw new WebException(StatusCode.UNKNOWN_HOST, e);
		} catch (IOException e) {
			throw new WebException(StatusCode.UNKNOWN_ERROR, e);
		}
	}

	/**
	 * Sends the provided message to the connection as uploaded data.
	 * 
	 * @param conn
	 *            The connection to send the data to.
	 * @param message
	 *            The data to upload.
	 */
	public static void writeToConnection(HttpURLConnection conn, String message) {
		OutputStreamWriter writer;
		try {
			writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(message);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new WebException(StatusCode.UNKNOWN_ERROR, e);
		}
	}

	/**
	 * Gets the response from the connection, Usually in the format of a JSON
	 * string.
	 * 
	 * @param conn
	 *            The connection to listen to.
	 * @return The response, usually as a JSON string.
	 */
	public static String getResponse(HttpURLConnection conn) {
		StringBuilder str = new StringBuilder();
		BufferedReader reader;
		try {
			if (conn.getResponseCode() != StatusCode.SUCCESS.getHttpCode()) {
				throw new WebException(conn.getResponseCode());
			}
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				str.append(line);
			}
			reader.close();
		} catch (IOException e) {
			throw new WebException(StatusCode.UNKNOWN_ERROR, e);
		}
		if (str.toString().equals("")) {
			throw new WebException(StatusCode.UNKNOWN_ERROR);
		}
		return str.toString();
	}
}
