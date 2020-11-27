package TRMS.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.User;
import TRMS.models.User.AuthPriv;
import TRMS.services.UserService;
import TRMS.services.UserServiceFullStack;
import io.javalin.http.Context;

public class UserController {

	private static Logger log = Logger.getRootLogger();
	
	private UserService service = new UserServiceFullStack();
	
	public void createUser(Context ctx) {
		
		try {
			
			String username = ctx.formParam("username");
			String password = ctx.formParam("password");
			int employeeId = Integer.parseInt(ctx.formParam("employee_id"));
			AuthPriv priviledge = AuthPriv.valueOf(ctx.formParam("priviledge"));
			
			User toCreate = new User(0, username, password, employeeId, priviledge);
			
			service.createUser(toCreate);
			
			log.info("Successfully created user: " + username);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when creating user: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
		
	}
	
	public void readUser(Context ctx) {
		
		try {
			
			int userId = Integer.parseInt(ctx.formParam("user_id"));
			
			ctx.json(service.readUser(userId));			
			
			log.info("Successfully created user: " + userId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading user: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
		
	}
	
	public void readAllUsers(Context ctx) {
		
		try {
			
			List<User> userList = new ArrayList<User>();
			
			userList = service.readAllUsers();
			
			ctx.json(userList);
			
			log.info("Successfully read all users");
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading all users: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void updateUser(Context ctx) {
		
		try {
			
			int userId = Integer.parseInt(ctx.formParam("user_id"));
			
			String username = ctx.formParam("username");
			String password = ctx.formParam("password");
			int employeeId = Integer.parseInt(ctx.formParam("employee_id"));
			AuthPriv priviledge = AuthPriv.valueOf(ctx.formParam("priviledge"));
			
			User toUpdate = new User(0, username, password, employeeId, priviledge);
			
			service.updateUser(userId, toUpdate);
			
			log.info("Successfully updated user: " + userId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when updating user: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void deleteUser(Context ctx) {
		
		try {
			
			int userId = Integer.parseInt(ctx.formParam("user_id"));
			
			String username = ctx.formParam("username");
			String password = ctx.formParam("password");
			int employeeId = Integer.parseInt(ctx.formParam("employee_id"));
			AuthPriv priviledge = AuthPriv.valueOf(ctx.formParam("priviledge"));
			
			User toDelete = new User(userId, username, password, employeeId, priviledge);
			
			service.deleteUser(toDelete);
			
			log.info("Successfully deleted user: " + username);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when deleting user: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
}
