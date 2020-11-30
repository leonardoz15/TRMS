/**
 * 
 */
package TRMS.javalin;



import org.apache.log4j.Logger;

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
	
	private static final String LOGIN_PATH = "login";
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> config.addStaticFiles("/public")).start(9091);
		
		log.info("Program has started");
		
		app.get("", ctx -> ctx.redirect("index.html"));
		app.get("home", ctx -> ctx.redirect("index.html"));
		
		app.post(LOGIN_PATH, ctx -> authController.login(ctx));
		app.get(LOGIN_PATH, ctx -> authController.checkUser(ctx));
		app.get("logout", ctx -> {authController.logout(ctx); ctx.redirect("index.html");});
		
		//Postman endpoints for testing:
		
		app.post("/createEmp", ctx -> employeeController.createEmployee(ctx));
		app.post("/createUser", ctx -> userController.createUser(ctx));
		app.post("/createRequest", ctx -> reimbursementController.createRequest(ctx));
		app.post("/createInfoRequest", ctx -> informationController.createInfoRequest(ctx));

	}

}
