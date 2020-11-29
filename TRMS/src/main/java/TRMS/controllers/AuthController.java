package TRMS.controllers;

import org.apache.log4j.Logger;

import TRMS.services.AuthService;
import TRMS.services.AuthServiceImpl;
import io.javalin.http.Context;

public class AuthController {
	
	private static Logger log = Logger.getRootLogger();
	
	private AuthService service = new AuthServiceImpl();
	
	private static final String TOKEN_NAME = "user_token";
	
	public void login(Context ctx) {
		
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(service.authenticateUser(username, password)) {
			log.info("Logged into user: "+ username);
			ctx.cookieStore(TOKEN_NAME, service.createToken(username));
			ctx.status(200);
			//TODO: Pull AuthLevel and handle redirection
		}
		else {
			ctx.redirect("login.html?error=failed-login");
		}
	}
	
	public boolean checkUser(Context ctx) {
		boolean auth = false;
        try {
            auth = service.validateToken(ctx.cookieStore(TOKEN_NAME));
        } catch (NullPointerException e) {
            log.warn("No cookie found for user: " + e);
        }
        return auth;
	}
	
	public void logout(Context ctx) {
		try {
			log.info("Logging out user");
			ctx.cookieStore("user_token", "");
		} catch (Exception e) {
			log.warn("Exception thrown when logging out "+ e);
		}
	}

}
