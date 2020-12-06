package TRMS.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.ReimbursementRequest;
import TRMS.models.ReimbursementRequest.ApprovalStatus;
import TRMS.models.ReimbursementRequest.EventType;
import TRMS.models.User.AuthPriv;
import TRMS.services.ReimbursementRequestFullStack;
import TRMS.services.ReimbursementRequestService;
import io.javalin.http.Context;

public class ReimbursementRequestController {
	
	private static Logger log = Logger.getRootLogger();
	
	private ReimbursementRequestService service = new ReimbursementRequestFullStack();
	
	private EmployeeController employeeController = new EmployeeController();
	
	public void createRequest(Context ctx) {
		
		try {
			
			int userId = Integer.parseInt(ctx.formParam("user_id"));
			double cost = Double.parseDouble(ctx.formParam("cost"));
			LocalDate date = LocalDate.parse(ctx.formParam("date"));
			LocalTime time = LocalTime.parse(ctx.formParam("time"));
			String location = ctx.formParam("location");
			String description = ctx.formParam("desription");
			String gradingFormat = ctx.formParam("grading_format");
			EventType eventType = EventType.valueOf(ctx.formParam("event_type"));
			boolean isUrgent = Boolean.parseBoolean(ctx.formParam("urgent"));
			double projected = Double.parseDouble(ctx.formParam("projected"));
			ApprovalStatus approvalStatus = ApprovalStatus.valueOf(ctx.formParam("status"));
			
			ReimbursementRequest toCreate = new ReimbursementRequest(0, userId, cost, date, time, location, description, gradingFormat,
																	eventType, isUrgent, projected, approvalStatus);
			
			service.createRequest(toCreate);
			
			
			log.info("Successfully created request for user " + userId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when creating request: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
		
	}
	
	public void readRequest(Context ctx) {
		//get request id from path, return json of request
		try {
			
			int requestId = Integer.parseInt(ctx.pathParam("id"));
		
			ctx.json(service.readRequest(requestId));		
			
			log.info("Successfully read request: " + requestId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading request: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void readAllRequests(Context ctx) {
		
		try {
			
			List<ReimbursementRequest> requestList = new ArrayList<ReimbursementRequest>();
			
			requestList = service.readAllRequests();

			ctx.json(requestList);
			
			log.info("Successfully read all requests");
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading all requests: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void updateRequest(Context ctx) {
		
		try {
			
			int requestId = Integer.parseInt(ctx.formParam("request_id"));
			
			int userId = Integer.parseInt(ctx.formParam("user_id"));
			double cost = Double.parseDouble(ctx.formParam("cost"));
			LocalDate date = LocalDate.parse(ctx.formParam("date"));
			LocalTime time = LocalTime.parse(ctx.formParam("time"));
			String location = ctx.formParam("location");
			String description = ctx.formParam("desription");
			String gradingFormat = ctx.formParam("grading_format");
			EventType eventType = EventType.valueOf(ctx.formParam("event_type"));
			boolean isUrgent = Boolean.parseBoolean(ctx.formParam("urgent"));
			double projected = Double.parseDouble(ctx.formParam("projected"));
			ApprovalStatus approvalStatus = ApprovalStatus.valueOf(ctx.formParam("status"));
			
			ReimbursementRequest toUpdate = new ReimbursementRequest(0, userId, cost, date, time, location, description, gradingFormat,
																	eventType, isUrgent, projected, approvalStatus);
			
			service.updateRequest(requestId, toUpdate);
			
			log.info("Successfully updated request: " + requestId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when updating request: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
		
	}
	
	public void deleteRequest(Context ctx) {
		//get request ID from path, read request by id, pass to delete, adds funds back 
		try {
			
			log.info("Starting to delete request in controller");
			int requestId = Integer.parseInt(ctx.pathParam("id"));
			
			ReimbursementRequest toDelete = service.readRequest(requestId);
			
			double cost = toDelete.getCost();
			employeeController.addFunds(ctx, cost);
			
			service.deleteRequest(toDelete);
			
			log.info("Successfully deleted request: " + requestId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when deleting request: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
		
	}
	
	public void newRequest(Context ctx) {
		//get user id, calculate projected, set default approval

		try {
	
			int userId = Integer.parseInt(ctx.cookieStore("userId"));
			double cost = Double.parseDouble(ctx.formParam("cost"));
			LocalDate date = LocalDate.parse(ctx.formParam("date"));
			LocalTime time = LocalTime.parse(ctx.formParam("time"));
			String location = ctx.formParam("location");
			String description = ctx.formParam("description");
			String gradingFormat = ctx.formParam("grading_format");
			EventType eventType = EventType.valueOf(ctx.formParam("event_type"));
			boolean isUrgent = Boolean.parseBoolean(ctx.formParam("urgent"));
			double projected = service.calculateProjected(cost, eventType);
			ApprovalStatus approvalStatus = ApprovalStatus.DS_PENDING;
		
			ReimbursementRequest toAdd = new ReimbursementRequest(0, userId, cost, date, time, location, description, gradingFormat,
					eventType, isUrgent, projected, approvalStatus);
			
			service.createRequest(toAdd);
			
			employeeController.removeFunds(ctx, cost);
			
			log.info("Successfully added request for user " + userId);
			ctx.status(200);
			if(ctx.cookieStore("priv").equals("EMPLOYEE")) {
				ctx.redirect("emp-dashboard.html");
			}
			else {
				ctx.redirect("approver-dashboard.html");
			}
			
			
		} catch (Exception e) {
			log.warn("Exception when adding a new request: " + e);
			ctx.status(500);
		}
	}
	
	public void getRequestsByUserId(Context ctx) {
		//get user id from cookie, read all requests in dao, json out list of requests with user id
		
		try {
			
			int userId = Integer.parseInt(ctx.cookieStore("userId"));
			
			List<ReimbursementRequest> requestList = new ArrayList<ReimbursementRequest>();
			List<ReimbursementRequest> userRequestList = new ArrayList<ReimbursementRequest>();
			
			requestList = service.readAllRequests();
			
			for(ReimbursementRequest request : requestList) {
				if(request.getUserId() == userId) {
					userRequestList.add(request);
				}
			}
			log.info("Successfully selected requests for user " + userId + " total: " + userRequestList.size());
			ctx.status(200);
			ctx.json(userRequestList);
			
		} catch (Exception e) {
			log.warn("Exception when selecting requests for user: " + e);
			ctx.status(500);
		}
		
	}
	
	public void getRequestsByUserPriv(Context ctx) {
		//get user priv from cookie, read all requests, filter out by status, return json list
		
		try {
			
			AuthPriv priv = AuthPriv.valueOf(ctx.cookieStore("priv"));
			
			List<ReimbursementRequest> requestList = new ArrayList<ReimbursementRequest>();
			List<ReimbursementRequest> approvalRequestList = new ArrayList<ReimbursementRequest>();
			
			requestList = service.readAllRequests();
			
			for(ReimbursementRequest request : requestList) {
				ApprovalStatus status = request.getApproval();
				
				switch(status) {
					case DS_PENDING:
						if(priv == AuthPriv.SUPERVISOR) {
							approvalRequestList.add(request);
						}
						break;
					case DH_PENDING:
						if(priv == AuthPriv.DEPT_HEAD) {
							approvalRequestList.add(request);
						}
						break;
					case BC_PENDING:
						if(priv == AuthPriv.BENCO || priv == AuthPriv.BENCO_SUP || priv == AuthPriv.ADMIN) {
							approvalRequestList.add(request);
						}
						break;
				}
				
			}
			log.info("Successfully selected requests for priv " + priv.toString() + " total: " + approvalRequestList.size());
			ctx.status(200);
			ctx.json(approvalRequestList);
			
		} catch (Exception e) {
			log.warn("Exception when selecting requests for priv: " + e);
			ctx.status(500);
		}
	}

}
