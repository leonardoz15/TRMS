/**
 * 
 */
package TRMS.models;

/**
 * @author Zachary Leonardo
 *
 */
public class User {
	
	public enum AuthPriv {
		EMPLOYEE,
		SUPERVISOR,
		DEPT_HEAD,
		BENCO,
		BENCO_SUP,
		ADMIN
	}
	
	private int userId;
	
	private String username;
	
	private String password;
	
	private int employeeId;
	
	private AuthPriv authLevel;

	
	public User() {
		super();
	}


	/**
	 * @param userId the unique id of this user
	 * @param username the username of this user
	 * @param password the password of this user
	 * @param employeeId the id of the employee this user represents
	 * @param authLevel the authorization level of the user
	 */
	public User(int userId, String username, String password, int employeeId, AuthPriv authLevel) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.employeeId = employeeId;
		this.authLevel = authLevel;
	}
	

	/**
	 * Constructor without user ID and default authorization
	 * @param username the username of this user
	 * @param password the password of this user
	 * @param employeeId the id of the employee this user represents
	 */
	public User(String username, String password, int employeeId) {
		super();
		this.username = username;
		this.password = password;
		this.employeeId = employeeId;
		this.authLevel = AuthPriv.EMPLOYEE;
	}


	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}


	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	/**
	 * @return the authLevel
	 */
	public AuthPriv getAuthLevel() {
		return authLevel;
	}


	/**
	 * @param authLevel the authLevel to set
	 */
	public void setAuthLevel(AuthPriv authLevel) {
		this.authLevel = authLevel;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", employeeId="
				+ employeeId + ", authLevel=" + authLevel + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authLevel == null) ? 0 : authLevel.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (authLevel != other.authLevel)
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
