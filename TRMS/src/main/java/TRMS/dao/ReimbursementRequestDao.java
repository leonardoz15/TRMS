/**
 * 
 */
package TRMS.dao;

import java.util.List;

import TRMS.models.ReimbursementRequest;

/**
 * @author Zachary Leonardo
 *
 */
public interface ReimbursementRequestDao {
	
	public void createRequest(ReimbursementRequest request);
	
	public ReimbursementRequest readRequest(int requestId);
	
	public List<ReimbursementRequest> readAllGuests();
	
	public ReimbursementRequest updateRequest(int requestId, ReimbursementRequest request);
	
	public void deleteRequest(ReimbursementRequest request);

}
