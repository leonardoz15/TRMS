/**
 * 
 */
package TRMS.models;

/**
 * @author Zachary Leonardo
 *
 */
public class Waitlist {
	
	private int priority;
	
	private int userId;
	
	private int requestId;


	public Waitlist() {
		super();
	}

	/**
	 * @param priority the priority of the request on the waitlist
	 * @param userId the user associated with this waitlist
	 * @param requestId the request associated with this waitlist
	 */
	public Waitlist(int priority, int userId, int requestId) {
		super();
		this.priority = priority;
		this.userId = userId;
		this.requestId = requestId;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
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
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "Waitlist [priority=" + priority + ", userId=" + userId + ", requestId=" + requestId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + priority;
		result = prime * result + requestId;
		result = prime * result + userId;
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
		Waitlist other = (Waitlist) obj;
		if (priority != other.priority)
			return false;
		if (requestId != other.requestId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}	
	
}
