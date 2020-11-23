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
	
	private int employeeId;
	
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
	public InformationRequest(int infoId, int requestId, int employeeId, String description) {
		super();
		this.infoId = infoId;
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.description = description;
	}


	@Override
	public String toString() {
		return "InformationRequest [infoId=" + infoId + ", requestId=" + requestId + ", employeeId=" + employeeId
				+ ", description=" + description + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + employeeId;
		result = prime * result + infoId;
		result = prime * result + requestId;
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
		if (employeeId != other.employeeId)
			return false;
		if (infoId != other.infoId)
			return false;
		if (requestId != other.requestId)
			return false;
		return true;
	}	

}
