/**
 * 
 */
package TRMS.models;

/**
 * @author Zachary Leonardo
 *
 */
public class InformationRequest {
	
	private int infoId;
	
	private int requestId;
	
	private int userId;
	
	private String description;
	

	public InformationRequest() {
		super();
	}


	/**
	 * @param infoId the unique id of this info request
	 * @param requestId the unique id of the reimbursement request that needs more info
	 * @param employeeId the unique id of the employee to receive the request
	 * @param description the information that is requested
	 */
	public InformationRequest(int infoId, int requestId, int userId, String description) {
		super();
		this.infoId = infoId;
		this.requestId = requestId;
		this.userId = userId;
		this.description = description;
	}

	
	/**
	 * @return the infoId
	 */
	public int getInfoId() {
		return infoId;
	}


	/**
	 * @param infoId the infoId to set
	 */
	public void setInfoId(int infoId) {
		this.infoId = infoId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "InformationRequest [infoId=" + infoId + ", requestId=" + requestId + ", userId=" + userId
				+ ", description=" + description + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + infoId;
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
		InformationRequest other = (InformationRequest) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (infoId != other.infoId)
			return false;
		if (requestId != other.requestId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
