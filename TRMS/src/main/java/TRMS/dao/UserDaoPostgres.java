/**
 * 
 */
package TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.User;
import TRMS.models.User.AuthPriv;
import TRMS.util.ConnectionUtil;

/**
 * @author Zachary Leonardo
 *
 */
public class UserDaoPostgres implements UserDao {
	
	private static Logger log = Logger.getRootLogger();
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	private PreparedStatement stmt;

	/**
	 * @param connUtil the connUtil to set
	 */
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public void createUser(User user) {
		
		String sql = "insert into users values (?, ?, ?, ?::auth_level)";
		
		log.info("Starting to insert employee with id " + user.getUserId());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getEmployeeId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getAuthLevel().toString());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one user created, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully inserted user " + user.getUserId());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User readUser(int userId) {
		
		User read;
		
		String sql = "select * from users where user_id = ?";
		
		log.info("Starting to read user with id " + userId);
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			read = new User(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getInt(2), AuthPriv.valueOf(rs.getString(5)));
			
			log.info("Successfully read user " + read.getUsername());
			
			return read;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading user with id " + userId);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> readAllUsers() {
		
		List<User> result = new ArrayList<User>();

		String sql = "select * from users";
		
		log.info("Starting to read all users");
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				User toAdd = new User(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getInt(2), AuthPriv.valueOf(rs.getString(5)));
				
				result.add(toAdd);
			}
			log.info("Successfully read all users, total: " + result.size());
			
			return result;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading all users");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User updateUser(int userId, User user) {

		String sql = "update users set employee_id = ?, username = ?, password = ?, auth_level = ?::auth_level where user_id = ?";
		
		log.info("Starting to update employee with id " + userId);
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getEmployeeId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getAuthLevel().toString());
			stmt.setInt(5, userId);
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one user updated, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully updated user " + userId);
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void deleteUser(User user) {
		
		String sql = "delete from users where user_id = ?";
		
		log.info("Starting to delete user with id " + user.getUserId());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getUserId());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one user deleted, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully deleted user " + user.getUserId());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
