/**
 * 
 */
package TRMS.services;

import java.util.List;

import TRMS.models.InformationRequest;

/**
 * @author Zachary Leonardo
 *
 */
public interface InformationRequestService {
	
	public void createInfoRequest(InformationRequest infoRequest);
	
	public InformationRequest readInfoRequest(int infoId);
	
	public List<InformationRequest> readAllInfoRequest();
	
	public InformationRequest updateInfoRequest(int infoId, InformationRequest infoRequest);
	
	public void deleteGuest(InformationRequest infoRequest);

}
