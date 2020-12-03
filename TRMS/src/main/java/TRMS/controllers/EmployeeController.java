package TRMS.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.Employee;
import TRMS.services.EmployeeService;
import TRMS.services.EmployeeServiceFullStack;
import io.javalin.http.Context;

public class EmployeeController {
	
	private static Logger log = Logger.getRootLogger();
	
	private EmployeeService service = new EmployeeServiceFullStack();
	
	public void createEmployee(Context ctx) {
		
		try {
			
			String firstName = ctx.formParam("first_name");
			String lastName = ctx.formParam("last_name");
			String phoneNumber = ctx.formParam("phone_number");
			String address = ctx.formParam("address");
			int reportsTo = Integer.valueOf(ctx.formParam("reports_to"));
			int funds = Integer.valueOf(ctx.formParam("funds"));
			
			Employee toCreate = new Employee(0, firstName, lastName, phoneNumber, address, reportsTo, funds);
			
			service.createEmployee(toCreate);
			
			log.info("Successfully created employee: " + firstName);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when creating employee: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
		
	}
	
	public void readEmployee(Context ctx) {
		
		try {
			
			int employeeId = Integer.parseInt(ctx.formParam("employee_id"));
		
			ctx.json(service.readEmployee(employeeId));		
			
			log.info("Successfully read employee: " + employeeId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading employee: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void readAllEmployees(Context ctx) {
		
		try {
			
			List<Employee> employeeList = new ArrayList<Employee>();
			
			employeeList = service.readAllEmployees();

			ctx.json(employeeList);
			
			log.info("Successfully read all employees");
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading all employees: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
		
	}
	
	public void updateEmployee(Context ctx) {
		
		try {
			
			int employeeId = Integer.parseInt(ctx.formParam("employee_id"));
			
			String firstName = ctx.formParam("first_name");
			String lastName = ctx.formParam("last_name");
			String phoneNumber = ctx.formParam("phone_number");
			String address = ctx.formParam("address");
			int reportsTo = Integer.valueOf(ctx.formParam("reports_to"));
			int funds = Integer.valueOf(ctx.formParam("funds"));
			
			
			Employee toUpdate = new Employee(0, firstName, lastName, phoneNumber, address, reportsTo, funds);
			
			service.updateEmployee(employeeId, toUpdate);
			
			log.info("Successfully updated employee: " + employeeId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when updating employee: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
		
	}
	
	public void deleteEmployee(Context ctx) {
		
		try {
			
			int employeeId = Integer.parseInt(ctx.formParam("employee_id"));
			
			String firstName = ctx.formParam("first_name");
			String lastName = ctx.formParam("last_name");
			String phoneNumber = ctx.formParam("phone_number");
			String address = ctx.formParam("address");
			int reportsTo = Integer.valueOf(ctx.formParam("reports_to"));
			int funds = Integer.valueOf(ctx.formParam("funds"));
			
			Employee toDelete = new Employee(employeeId, firstName, lastName, phoneNumber, address, reportsTo, funds);
			
			service.deleteEmployee(toDelete);
			
			log.info("Successfully deleted employee: " + firstName);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when deleting employee: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
		
	}
	
	public void getEmployee(Context ctx) {
		log.info("Getting employee");
		String empId = ctx.pathParam("id");
		Employee employee = service.readEmployee(Integer.parseInt(empId));
		ctx.json(employee);
	}
	
	public void addFunds(Context ctx) {
		//add funds back to the emp in dao
		log.info("Adding funds back to logged in user");
		int empId = Integer.parseInt(ctx.cookieStore("empId"));
		Employee read = service.readEmployee(empId);
		
		try {
			
		} catch (Exception e) {
			log.warn("Exception thrown when adding funds: " + e);
			ctx.status(500);
		}
	}
	
	public void removeFunds(Context ctx) {
		//remove funds from emp in dao
		log.info("removing funds from logged in user");
		int empId = Integer.parseInt(ctx.cookieStore("empId"));
		Employee read = service.readEmployee(empId);
		//read.setFunds(funds);
		try {
			service.updateEmployee(empId, read);
			
		} catch (Exception e) {
			log.warn("Exception thrown when removing funds: " + e);
			ctx.status(500);
		}
	}
	public void getBalance(Context ctx) {
		
		log.info("Reading balance for logged in user");
		
		int empId = Integer.parseInt(ctx.cookieStore("empId"));
		Employee read = service.readEmployee(empId);
		
		ctx.json(read.getFunds());
		log.info("Funds for emp: "+empId+" = "+read.getFunds());
		
	}

}
