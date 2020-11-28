package TRMS.controllers;

import org.apache.log4j.Logger;

import TRMS.services.AuthService;
import TRMS.services.AuthServiceImpl;

public class AuthController {
	
	private static Logger log = Logger.getRootLogger();
	
	private AuthService service = new AuthServiceImpl();
	
	

}
