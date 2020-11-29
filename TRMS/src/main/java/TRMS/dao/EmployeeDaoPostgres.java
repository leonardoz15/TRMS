/**
 * 
 */
package TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.Employee;
import TRMS.util.ConnectionUtil;

/**
 * @author Zachary Leonardo
 *
 */
public class EmployeeDaoPostgres implements EmployeeDao {
	
	private static Logger log = Logger.getRootLogger();
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	private PreparedStatement stmt;

	/**
	 * @param connUtil the connUtil to set
	 */
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public void createEmployee(Employee employee) {

		String sql = "insert into employee values (default, ?, ?, ?, ?, ?)";
		
		log.info("Starting to insert employee with id " + employee.getEmployeeID());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, employee.getFirstName());
			stmt.setString(2, employee.getLastName());
			stmt.setString(3, employee.getPhoneNumber());
			stmt.setString(4, employee.getAddress());
			stmt.setInt(5, employee.getReportsTo());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one employee created, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully inserted employee " + employee.getEmployeeID());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when creating employee: " + e);
			e.printStackTrace();
		}

	}

	@Override
	public Employee readEmployee(int employeeId) {
		
		Employee read;
		
		String sql = "select * from employee where employee_id = ?";
		
		log.info("Starting to read employee with id " + employeeId);
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			
			ResultSet rs = stmt.executeQuery();
			read = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), Integer.toString(rs.getInt(4)), 
										rs.getString(5), rs.getInt(6));
			
			log.info("Successfully read employee " + read.getFirstName());
			
			return read;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading employee with id " + employeeId);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Employee> readAllEmployees() {

		List<Employee> result = new ArrayList<Employee>();
		
		String sql = "select * from employee";
		
		log.info("Starting to read all employees");
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Employee toAdd = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), Integer.toString(rs.getInt(4)), 
										rs.getString(5), rs.getInt(6));
				result.add(toAdd);
			}
			log.info("Successfully read all employees, total: " + result.size());
			
			return result;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading all employees");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Employee updateEmployee(int employeeId, Employee employee) {
		
		String sql = "update employee set first_name = ?, last_name = ?, phone_number = ?, address = ?, "
					+ "reports_to = ? where employee_id = ?";
		
		log.info("Starting to update employee with id " + employeeId);
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, employee.getFirstName());
			stmt.setString(2, employee.getLastName());
			stmt.setInt(3, Integer.parseInt(employee.getPhoneNumber()));
			stmt.setString(4, employee.getAddress());
			stmt.setInt(5, employee.getReportsTo());
			stmt.setInt(6, employeeId);
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one employee updated, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully updated employee " + employee.getEmployeeID());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}

	@Override
	public void deleteEmployee(Employee employee) {
		
		String sql = "delete from employee where employee_id = ?";
		
		log.info("Starting to delete employee with id " + employee.getEmployeeID());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employee.getEmployeeID());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one account deleted, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully deleted employee " + employee.getEmployeeID());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
