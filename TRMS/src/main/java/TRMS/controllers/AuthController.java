package TRMS.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import TRMS.services.AuthService;
import TRMS.services.AuthServiceImpl;
import TRMS.services.UserService;
import TRMS.services.UserServiceFullStack;
import TRMS.models.*;
import TRMS.models.User.AuthPriv;
import io.javalin.http.Context;

public class AuthController {
	
	private static Logger log = Logger.getRootLogger();
	
	private AuthService service = new AuthServiceImpl();
	
	private UserService userService = new UserServiceFullStack();
	
	private static final String TOKEN_NAME = "user_token";
	
	public void login(Context ctx) {
		ctx.cookieStore(TOKEN_NAME, "");
		
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(service.authenticateUser(username, password)) {
			log.info("Logged into user: "+ username);
			ctx.cookieStore(TOKEN_NAME, service.createToken(username));
			ctx.status(200);
			User loggedIn = userService.readUserByLogin(username, password);
			String priviledge = loggedIn.getAuthLevel().toString();
			ctx.cookieStore("priv", priviledge);
			ctx.cookieStore("userId",Integer.toString(loggedIn.getUserId()));
			ctx.cookieStore("empId", Integer.toString(loggedIn.getEmployeeId()));
			
			switch(priviledge) {
				case "EMPLOYEE":
					ctx.redirect("emp-dashboard.html");
					break;
				case "SUPERVISOR":
					ctx.redirect("approver-dashboard.html");
					break;
				case "DEPT_HEAD":
					ctx.redirect("approver-dashboard.html");
					break;
				case "BENCO":
					ctx.redirect("approver-dashboard.html");
					break;
				case "BENCO_SUP":
					ctx.redirect("approver-dashboard.html");
					break;
				case "ADMIN":
					ctx.redirect("approver-dashboard.html");
					break;	
			}
		}
		else {
			ctx.redirect("login.html?error=failed-login");
		}
	}
	
	public boolean checkUser(Context ctx) {
		boolean auth = false;
        try {
        	log.info("Checking user token");
        	if(!ctx.cookieStore(TOKEN_NAME).equals("")) {
        		auth = service.validateToken(ctx.cookieStore(TOKEN_NAME));
        	}
        } catch (NullPointerException e) {
            log.warn("No cookie found for user: " + e);
        }
        ctx.json(auth);
        return auth;
	}
	
	public void logout(Context ctx) {
		try {
			log.info("Logging out user");
			ctx.clearCookieStore();
		} catch (Exception e) {
			log.warn("Exception thrown when logging out "+ e);
		}
	}
	

}
