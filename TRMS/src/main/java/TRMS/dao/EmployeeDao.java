/**
 * 
 */
package TRMS.dao;

import java.util.List;

import TRMS.models.Employee;

/**
 * @author Zachary Leonardo
 *
 */
public interface EmployeeDao {

	public void createEmployee(Employee employee);
	
	public Employee readEmployee(int employeeId);
	
	public List<Employee> readAllEmployees();
	
	public Employee updateEmployee(int employeeId, Employee employee);
	
	public void deleteEmployee(Employee employee);
	
}
