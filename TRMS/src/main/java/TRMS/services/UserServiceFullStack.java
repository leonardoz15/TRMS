/**
 * 
 */
package TRMS.services;

import java.util.List;

import org.apache.log4j.Logger;

import TRMS.dao.UserDao;
import TRMS.dao.UserDaoPostgres;
import TRMS.models.User;

/**
 * @author Zachary Leonardo
 *
 */
public class UserServiceFullStack implements UserService {
	
	private UserDao userDao = new UserDaoPostgres();
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public void createUser(User user) {
		
		log.info("User service creating user");

		userDao.createUser(user);
	}

	@Override
	public User readUser(int userId) {
		
		log.info("User service reading user");
		
		return userDao.readUser(userId);
	}
	

	@Override
	public User readUserByLogin(String username, String password) {

		log.info("User service reading user by login");
		
		return userDao.readUserByLogin(username, password);
	}

	@Override
	public List<User> readAllUsers() {
		
		log.info("User service reading all users");
		
		return userDao.readAllUsers();
	}

	@Override
	public User updateUser(int userId, User user) {
		
		log.info("User service updating user");
		
		return userDao.updateUser(userId, user);
	}

	@Override
	public void deleteUser(User user) {

		log.info("User service deleting user");
		
		userDao.deleteUser(user);

	}

}
