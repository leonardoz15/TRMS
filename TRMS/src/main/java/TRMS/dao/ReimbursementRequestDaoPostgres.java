/**
 * 
 */
package TRMS.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.ReimbursementRequest;
import TRMS.util.ConnectionUtil;

/**
 * @author Zachary Leonardo
 *
 */
public class ReimbursementRequestDaoPostgres implements ReimbursementRequestDao {
	
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
	public void createRequest(ReimbursementRequest request) {
		
		String sql = "insert into request values (?,?,?,?,?,?,?,?::event_type,?,?,?::approval_status)";
		
		log.info("Starting to insert request with id " + request.getRequestId());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, request.getUserId());
			stmt.setDouble(2, request.getCost());
			stmt.setDate(3, Date.valueOf(LocalDate.from(request.getDate())));
			stmt.setTime(4, Time.valueOf(LocalTime.from(request.getTime())));
			stmt.setString(5, request.getLocation());
			stmt.setString(6, request.getDescription());
			stmt.setString(7, request.getGradingFormat());
			stmt.setString(8, request.getEventType().toString());
			stmt.setBoolean(9, request.isUrgent());
			stmt.setDouble(10, request.getProjected());
			stmt.setString(11, request.getApproval().toString());
			
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one request created, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully inserted request " + request.getRequestId());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ReimbursementRequest readRequest(int requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementRequest> readAllGuests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReimbursementRequest updateRequest(int requestId, ReimbursementRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRequest(ReimbursementRequest request) {
		// TODO Auto-generated method stub

	}

}
