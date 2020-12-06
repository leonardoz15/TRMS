/**
 * 
 */
package TRMS.javalin;



import org.apache.log4j.Logger;

import TRMS.controllers.AttachmentController;
import TRMS.controllers.AuthController;
import TRMS.controllers.EmployeeController;
import TRMS.controllers.InformationRequestController;
import TRMS.controllers.ReimbursementRequestController;
import TRMS.controllers.UserController;
import io.javalin.Javalin;

/**
 * @author Zachary Leonardo
 *
 */
public class ServerDriver {

	private static Logger log = Logger.getRootLogger();
	
	private static AuthController authController = new AuthController();
	private static EmployeeController employeeController = new EmployeeController();
	private static UserController userController = new UserController();
	private static ReimbursementRequestController reimbursementController = new ReimbursementRequestController();
	private static InformationRequestController informationController = new InformationRequestController();
	private static AttachmentController attachmentController = new AttachmentController();
	
	private static final String LOGIN_PATH = "login";
	private static final String USER_PATH = "user";
	private static final String EMPLOYEE_PATH = "employee";
	private static final String REQUEST_PATH = "request";
	private static final String ATTACH_PATH = "attachment";
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> config.addStaticFiles("/public")).start(9091);
		
		log.info("Program has started");
		
		app.get("", ctx -> ctx.redirect("index.html"));
		app.get("home", ctx -> ctx.redirect("index.html"));
		
		app.post(LOGIN_PATH, ctx -> authController.login(ctx));
		app.get(LOGIN_PATH, ctx -> authController.checkUser(ctx));
		app.get("logout", ctx -> {authController.logout(ctx); ctx.redirect("index.html");});
		app.get(EMPLOYEE_PATH +"/:id", ctx -> employeeController.getEmployee(ctx));
		app.get(EMPLOYEE_PATH+"Balance", ctx -> employeeController.getBalance(ctx));
		app.post(REQUEST_PATH, ctx -> reimbursementController.newRequest(ctx));
		app.get(REQUEST_PATH, ctx -> reimbursementController.getRequestsByUserId(ctx));
		app.get(REQUEST_PATH+"/:id", ctx -> reimbursementController.readRequest(ctx));
		app.delete(REQUEST_PATH+"/:id", ctx -> reimbursementController.deleteRequest(ctx));
		app.post(ATTACH_PATH, ctx -> attachmentController.createAttachment(ctx));
		app.get(REQUEST_PATH+"Approval", ctx -> reimbursementController.getRequestsByUserPriv(ctx));
		app.put(REQUEST_PATH+"Approve/:id", ctx -> reimbursementController.approveRequestById(ctx));
		app.put(REQUEST_PATH+"Deny/:id", ctx -> reimbursementController.denyRequestById(ctx));
		
		//Postman endpoints for testing: 
		
		app.post("/createEmp", ctx -> employeeController.createEmployee(ctx));
		app.post("/createUser", ctx -> userController.createUser(ctx));
		app.post("/createRequest", ctx -> reimbursementController.createRequest(ctx));
		app.post("/createInfoRequest", ctx -> informationController.createInfoRequest(ctx));
		app.get("/getEmp", ctx -> employeeController.readEmployee(ctx));
		app.get("/getUser", ctx -> userController.readUser(ctx));
		app.get("/hello", ctx -> ctx.html("hello there"));
	}

}
