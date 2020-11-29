/**
 * 
 */
package TRMS.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import TRMS.models.User;

/**
 * @author Zachary Leonardo
 *
 */
public class AuthServiceImpl implements AuthService {
	
	private static Logger log = Logger.getRootLogger();
	
	private static byte[] salt = new SecureRandom().getSeed(16);
	
	private Map<String, String> tokenRepo = new HashMap<>();
	
	private UserService userService = new UserServiceFullStack();

	@Override
	public boolean authenticateUser(String username, String password) {
		//pull all users and see if user exists, return true or false
		log.info("Authenticating user with username: "+ username +" password: "+ password);
		
		List<User> usersList = new ArrayList<User>();
		
		usersList = userService.readAllUsers();
		
		for (User u : usersList) {
			if(u.getUsername() == username & u.getPassword() == password) {
				return true;
			}
		}
		log.warn("No user exists in database with username: "+ username +" password: "+ password);
		return false;
	}

	@Override
	public String createToken(String username) {
		String token = simpleHash(username);
		tokenRepo.put(token, username);
		return token;
	}

	@Override
	public boolean validateToken(String token) {
		try {
			tokenRepo.get(token);
			return true;
		} catch (NullPointerException e) {
			return false;
		}		
	}
	
	private String simpleHash(String username) {
		
		String hash = null;
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			
			byte[] bytes = md.digest(username.getBytes());

			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(0));
			}
			
			hash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hash;
	}

}
