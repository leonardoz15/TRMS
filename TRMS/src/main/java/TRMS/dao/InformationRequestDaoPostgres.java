/**
 * 
 */
package TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.InformationRequest;
import TRMS.util.ConnectionUtil;

/**
 * @author Zachary Leonardo
 *
 */
public class InformationRequestDaoPostgres implements InformationRequestDao {
	
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
	public void createInfoRequest(InformationRequest infoRequest) {
		
		String sql = "insert into info_req values(?, ?, ?)";
		
		log.info("Starting to insert info request with id " + infoRequest.);
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getEmployeeId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getAuthLevel().toString());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one user created, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully inserted user " + user.getUserId());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public InformationRequest readInfoRequest(int infoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InformationRequest> readAllInfoRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InformationRequest updateInfoRequest(InformationRequest infoRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGuest(InformationRequest infoRequest) {
		// TODO Auto-generated method stub

	}

}
