package TRMS.dao.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import TRMS.dao.EmployeeDao;
import TRMS.dao.EmployeeDaoPostgres;
import TRMS.models.Employee;
import TRMS.util.ConnectionUtil;

public class EmployeeDaoTest {
	
	@Mock
	private ConnectionUtil connUtil;
	
	@Mock
	private Connection connection;
	
	private Connection realConn;
	private PreparedStatement stmt;
	private PreparedStatement testStmt;
	private PreparedStatement spy;
	
	private EmployeeDaoPostgres employeeDao;
	private Employee employee;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		realConn = new ConnectionUtil().createConnection();
		employeeDao = new EmployeeDaoPostgres();
		employeeDao.setConnUtil(connUtil);
		employee = new Employee(1001, "John", "Doe", "573674930", "123 Nowhere St.", 1);
	}

	@After
	public void tearDown() throws Exception {
		realConn.close();
	}

	@Test
	public void createEmployeeTest() {
		
		try {
			
			String sql = "insert into employee values (?, ?, ?, ?, ?)";
			
			try {
				initStmtHelper(sql);
			} catch (SQLException e) {
				fail("Exception thrown in test setup ");
			}
			
			try {
				employeeDao.createEmployee(employee);
				
				verify(spy).setString(1, employee.getFirstName());
				verify(spy).setString(2, employee.getLastName());
				verify(spy).setInt(3, Integer.parseInt(employee.getPhoneNumber()));
				verify(spy).setString(4, employee.getAddress());
				verify(spy).setInt(5, employee.getReportsTo());
				
				verify(spy).executeQuery();
				//dependent on read method
				assertNotEquals(employeeDao.readEmployee(1001), employee);
				
			} catch (SQLException e) {
				fail("Exception thrown in test process");
			}
		} finally {
			try {
				testStmt = realConn.prepareStatement("delete from employee where employee_id = ?");
				testStmt.setInt(1, 1001);
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("Exception thrown in test cleanup");
			}
			
		}
	}
	
	
	@Test
	public void readEmployeeTest() {
		
	}
	
	private void initStmtHelper(String sql) throws SQLException {
		//Prep Mockito Spy
		stmt = realConn.prepareStatement(sql);
		spy = Mockito.spy(stmt);

		//Set standard connection mocking methods
		when(connUtil.createConnection()).thenReturn(connection);
		when(connection.prepareStatement(sql)).thenReturn(spy);
	}

}
