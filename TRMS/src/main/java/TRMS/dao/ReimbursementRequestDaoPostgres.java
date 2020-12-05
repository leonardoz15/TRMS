/**
 * 
 */
package TRMS.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.ReimbursementRequest;
import TRMS.models.ReimbursementRequest.ApprovalStatus;
import TRMS.models.ReimbursementRequest.EventType;
import TRMS.models.User;
import TRMS.models.User.AuthPriv;
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
		
		String sql = "insert into request values (default,?,?,?,?,?,?,?,?::event_type,?,?,?::approval_status)";
		
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
		
		ReimbursementRequest read = null;
		
		String sql = "select * from request where request_id = ?";
		
		log.info("Starting to read request with id " + requestId);
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, requestId);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
			read = new ReimbursementRequest(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4).toLocalDate(), rs.getTime(5).toLocalTime(),
											rs.getString(6), rs.getString(7), rs.getString(8), EventType.valueOf(rs.getString(9)), rs.getBoolean(10),
											rs.getDouble(11), ApprovalStatus.valueOf(rs.getString(12)));
			
			}
			
			log.info("Successfully read request " + read.getRequestId());
			
			return read;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading request with id " + requestId);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<ReimbursementRequest> readAllRequests() {
		
		List<ReimbursementRequest> result = new ArrayList<ReimbursementRequest>();
		
		String sql = "select * from request";
		
		log.info("Starting to read all requests");
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ReimbursementRequest toAdd = new ReimbursementRequest(
						rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4).toLocalDate(), rs.getTime(5).toLocalTime(),
						rs.getString(6), rs.getString(7), rs.getString(8), EventType.valueOf(rs.getString(9)), rs.getBoolean(10),
						rs.getDouble(11), ApprovalStatus.valueOf(rs.getString(12)));
				
				result.add(toAdd);
			}
			log.info("Successfully read all requests, total: " + result.size());
			
			return result;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading all requests");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ReimbursementRequest updateRequest(int requestId, ReimbursementRequest request) {
		
		String sql = "update request set user_id = ?, cost = ?, date = ?, time = ?, location = ?, description = ?, grading_format = ?, "
						+ "event_type = ?::event_type, isUrgent = ?, projected_amount = ?, approval_status = ?::approval_status where"
						+ " request_id = ?";
		
		log.info("Starting to update request with id " + requestId);
		
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
			stmt.setInt(12, requestId);
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one request updated, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully updated request " + requestId);
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return request;
	}

	@Override
	public void deleteRequest(ReimbursementRequest request) {
		
		String sql = "delete from request where request_id = ?";
		
		log.info("Starting to delete request with id " + request.getRequestId());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, request.getRequestId());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one request deleted, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully deleted request " + request.getRequestId());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
