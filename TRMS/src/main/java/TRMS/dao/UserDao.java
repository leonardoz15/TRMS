/**
 * 
 */
package TRMS.dao;

import java.util.List;

import TRMS.models.User;

/**
 * @author Zachary Leonardo
 *
 */
public interface UserDao {
	
	public void createUser(User user);
	
	public User readUser(int userId);
	
	public User readUserByLogin(String username, String password);
	
	public List<User> readAllUsers();
	
	public User updateUser(int userId, User user);
	
	public void deleteUser(User user);

}
