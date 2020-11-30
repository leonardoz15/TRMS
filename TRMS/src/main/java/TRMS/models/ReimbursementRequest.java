/**
 * 
 */
package TRMS.models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Zachary Leonardo
 *
 */
public class ReimbursementRequest {
	
	public enum EventType {
		UNI_COURSE,
		SEMINAR,
		CERT_PREP_CLASS,
		CERTIFICATION,
		TECHNICAL_TRAINING,
		OTHER
	}
	
	public enum ApprovalStatus {
		DS_PENDING,
		DH_PENDING,
		BC_PENDING,
		INFO_NEEDED,
		DENIED,
		APPROVED
	}
	
	private int requestId;
	
	private int userId;
	
	private double cost;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private String location;
	
	private String description;
	
	private String gradingFormat;
	
	private EventType eventType;
	
	private boolean isUrgent;
	
	private double projected;
	
	private ApprovalStatus approval;
	

	public ReimbursementRequest() {
		super();
	}


	/** Constructor with defaults:
	 * @param requestId
	 * @param userId
	 * @param cost
	 * @param date set to current date
	 * @param time set to current time
	 * @param location
	 * @param description
	 * @param gradingFormat
	 * @param eventType
	 * @param isUrgent set to false
	 * @param projected set to 0.0
	 * @param approval set to DS_PENDING
	 */
	public ReimbursementRequest(int requestId, int userId, double cost,
			String location, String description, String gradingFormat, EventType eventType) {
		super();
		this.requestId = requestId;
		this.userId = userId;
		this.cost = cost;
		this.date = LocalDate.now();
		this.time = LocalTime.now();
		this.location = location;
		this.description = description;
		this.gradingFormat = gradingFormat;
		this.eventType = eventType;
		this.isUrgent = false;
		this.projected = 0.0;
		this.approval = ApprovalStatus.DS_PENDING;
	}


	/** No default constructor
	 * @param requestId
	 * @param userId
	 * @param cost
	 * @param date
	 * @param time
	 * @param location
	 * @param description
	 * @param gradingFormat
	 * @param eventType
	 * @param isUrgent
	 * @param projected
	 * @param approval
	 */
	public ReimbursementRequest(int requestId, int userId, double cost, LocalDate date, LocalTime time,
			String location, String description, String gradingFormat, EventType eventType, boolean isUrgent,
			double projected, ApprovalStatus approval) {
		super();
		this.requestId = requestId;
		this.userId = userId;
		this.cost = cost;
		this.date = date;
		this.time = time;
		this.location = location;
		this.description = description;
		this.gradingFormat = gradingFormat;
		this.eventType = eventType;
		this.isUrgent = isUrgent;
		this.projected = projected;
		this.approval = approval;
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
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}


	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}


	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}


	/**
	 * @return the time
	 */
	public LocalTime getTime() {
		return time;
	}


	/**
	 * @param time the time to set
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}


	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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


	/**
	 * @return the gradingFormat
	 */
	public String getGradingFormat() {
		return gradingFormat;
	}


	/**
	 * @param gradingFormat the gradingFormat to set
	 */
	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}


	/**
	 * @return the eventType
	 */
	public EventType getEventType() {
		return eventType;
	}


	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}


	/**
	 * @return the isUrgent
	 */
	public boolean isUrgent() {
		return isUrgent;
	}


	/**
	 * @param isUrgent the isUrgent to set
	 */
	public void setUrgent(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}


	/**
	 * @return the projected
	 */
	public double getProjected() {
		return projected;
	}


	/**
	 * @param projected the projected to set
	 */
	public void setProjected(double projected) {
		this.projected = projected;
	}


	/**
	 * @return the approval
	 */
	public ApprovalStatus getApproval() {
		return approval;
	}


	/**
	 * @param approval the approval to set
	 */
	public void setApproval(ApprovalStatus approval) {
		this.approval = approval;
	}


	@Override
	public String toString() {
		return "ReimbursementRequest [requestId=" + requestId + ", userId=" + userId + ", cost=" + cost + ", date="
				+ date + ", time=" + time + ", location=" + location + ", description=" + description
				+ ", gradingFormat=" + gradingFormat + ", eventType=" + eventType + ", isUrgent=" + isUrgent
				+ ", projected=" + projected + ", approval=" + approval + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approval == null) ? 0 : approval.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((gradingFormat == null) ? 0 : gradingFormat.hashCode());
		result = prime * result + (isUrgent ? 1231 : 1237);
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		temp = Double.doubleToLongBits(projected);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + requestId;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		ReimbursementRequest other = (ReimbursementRequest) obj;
		if (approval != other.approval)
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eventType != other.eventType)
			return false;
		if (gradingFormat == null) {
			if (other.gradingFormat != null)
				return false;
		} else if (!gradingFormat.equals(other.gradingFormat))
			return false;
		if (isUrgent != other.isUrgent)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (Double.doubleToLongBits(projected) != Double.doubleToLongBits(other.projected))
			return false;
		if (requestId != other.requestId)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
