/**
 * 
 */
package TRMS.javalin;



import org.apache.log4j.Logger;

import TRMS.controllers.AuthController;
import TRMS.controllers.EmployeeController;
import TRMS.controllers.InformationRequestController;
import TRMS.controllers.ReimbursementRequestController;
import io.javalin.Javalin;

/**
 * @author Zachary Leonardo
 *
 */
public class ServerDriver {

	private static Logger log = Logger.getRootLogger();
	
	private static AuthController authController = new AuthController();
	private static EmployeeController employeeController = new EmployeeController();
	private static ReimbursementRequestController reimbursementController = new ReimbursementRequestController();
	private static InformationRequestController informationController = new InformationRequestController();
	
	private static final String LOGIN_PATH = "/login";
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> config.addStaticFiles("/public")).start(9091);
		
		log.info("Program has started");
		
		app.get("", ctx -> ctx.redirect("index.html"));
		app.get("home", ctx -> ctx.redirect("index.html"));
		
		app.post("/create", ctx -> employeeController.createEmployee(ctx));
		

	}

}
