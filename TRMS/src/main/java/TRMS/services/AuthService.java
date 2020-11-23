/**
 * 
 */
package TRMS.services;

/**
 * @author Zachary Leonardo
 *
 */
public interface AuthService {
	
		public boolean authenticateUser(String username, String password);
		
		public String createToken(String username);
		
		public String validateToken(String token);

}
