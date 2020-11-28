package TRMS.controllers;

import org.apache.log4j.Logger;

import TRMS.services.AuthService;
import TRMS.services.AuthServiceImpl;
import io.javalin.http.Context;

public class AuthController {
	
	private static Logger log = Logger.getRootLogger();
	
	private AuthService service = new AuthServiceImpl();
	
	public void login(Context ctx) {
		
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(service.authenticateUser(username, password)) {
			log.info("Logged into user: "+ username);
			ctx.cookieStore("user_token", service.createToken(username));
			ctx.status(200);
			//TODO: Pull AuthLevel and handle redirection
		}
	}
	
	public void logout(Context ctx) {
		
	}

}
