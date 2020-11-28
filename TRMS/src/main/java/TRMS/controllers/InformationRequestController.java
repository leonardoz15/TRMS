package TRMS.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.InformationRequest;
import TRMS.services.InformationRequestFullStack;
import TRMS.services.InformationRequestService;
import io.javalin.http.Context;

public class InformationRequestController {
	
	private static Logger log = Logger.getRootLogger();
	
	private InformationRequestService service = new InformationRequestFullStack();
	
	public void createInfoRequest(Context ctx) {
		
		try {
			
			int requestId = Integer.parseInt(ctx.formParam("request_id"));
			int employeeId = Integer.parseInt(ctx.formParam("employee_id"));
			String description = ctx.formParam("description");
			
			InformationRequest toCreate = new InformationRequest(0, requestId, employeeId, description);
			
			service.createInfoRequest(toCreate);
			
			log.info("Successfully created info request for reimbursement: " + requestId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when creating info request: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void readInfoRequest(Context ctx) {
		
		try {
			
			int infoId = Integer.parseInt(ctx.formParam("info_id"));
			
			ctx.json(service.readInfoRequest(infoId));
			
			log.info("Successfully read info request: " + infoId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading info request: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void readAllInfoRequests(Context ctx) {
		
		try {
			
			List<InformationRequest> infoList = new ArrayList<InformationRequest>();
			
			infoList = service.readAllInfoRequest();
			
			ctx.json(infoList);
			
			log.info("Successfully read all info requests");
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading all info requests: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void updateInfoRequest(Context ctx) {
		
		try {
			
			int infoId = Integer.parseInt(ctx.formParam("info_id"));
			
			int requestId = Integer.parseInt(ctx.formParam("request_id"));
			int employeeId = Integer.parseInt(ctx.formParam("employee_id"));
			String description = ctx.formParam("description");
			
			InformationRequest toUpdate = new InformationRequest(0, requestId, employeeId, description);
			
			service.updateInfoRequest(infoId, toUpdate);
			
			log.info("Successfully updated info request: " + infoId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when updating info request: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void deleteInfoRequest(Context ctx) {
		
		try {
			
			int infoId = Integer.parseInt(ctx.formParam("info_id"));
			
			int requestId = Integer.parseInt(ctx.formParam("request_id"));
			int employeeId = Integer.parseInt(ctx.formParam("employee_id"));
			String description = ctx.formParam("description");
			
			InformationRequest toDelete = new InformationRequest(infoId, requestId, employeeId, description);
			
			service.deleteRequest(toDelete);
			
			log.info("Successfully deleted info request: " + infoId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when deleting info request: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}

}
