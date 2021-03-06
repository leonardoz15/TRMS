package TRMS.services;

import java.util.List;

import org.apache.log4j.Logger;

import TRMS.dao.EmployeeDao;
import TRMS.dao.EmployeeDaoPostgres;
import TRMS.models.Employee;

public class EmployeeServiceFullStack implements EmployeeService {
	
	private EmployeeDao employeeDao = new EmployeeDaoPostgres();
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public void createEmployee(Employee employee) {
		
		log.info("Employee service creating employee");
		
		employeeDao.createEmployee(employee);
		
	}

	@Override
	public Employee readEmployee(int employeeId) {

		log.info("Employee service reading employee");
		
		return employeeDao.readEmployee(employeeId);
		
	}

	@Override
	public List<Employee> readAllEmployees() {
		
		log.info("Employee service reading all employees");
		
		return employeeDao.readAllEmployees();
	}

	@Override
	public Employee updateEmployee(int employeeId, Employee employee) {
		
		log.info("Employee service updating employee");
		
		return employeeDao.updateEmployee(employeeId, employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		
		log.info("Employee service deleting employee");
		
		employeeDao.deleteEmployee(employee);

	}

}
