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

import TRMS.models.InformationRequest;
import TRMS.models.User;
import TRMS.models.User.AuthPriv;
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
		
		String sql = "insert into info_req values(default, ?, ?, ?)";
		
		log.info("Starting to insert info request with id " + infoRequest.getInfoId());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, infoRequest.getRequestId());
			stmt.setInt(2, infoRequest.getUserId());
			stmt.setString(3, infoRequest.getDescription());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one info request created, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully inserted info request " + infoRequest.getInfoId());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public InformationRequest readInfoRequest(int infoId) {
		
		InformationRequest read;
		
		String sql = "select * from info_req where info_id = ?";
		
		log.info("Starting to read info request with id " + infoId);
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, infoId);
			
			ResultSet rs = stmt.executeQuery();
			read = new InformationRequest(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
			
			log.info("Successfully read info request " + read.getInfoId());
			
			return read;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading info request with id " + infoId);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<InformationRequest> readAllInfoRequest() {
		
		List<InformationRequest> result = new ArrayList<InformationRequest>();
		
		String sql = "select * from info_req";
		
		log.info("Starting to read all info requests");
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				InformationRequest toAdd = new InformationRequest(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
				
				result.add(toAdd);
			}
			log.info("Successfully read all info requests, total: " + result.size());
			
			return result;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading all info requests");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public InformationRequest updateInfoRequest(int infoId, InformationRequest infoRequest) {
		
		String sql = "update info_req set request_id = ?, employee_id = ?, description = ? where info_id = ?";
		
		log.info("Starting to update info request with id " + infoId);
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,infoRequest.getRequestId());
			stmt.setInt(2, infoRequest.getUserId());
			stmt.setString(3, infoRequest.getDescription());
			stmt.setInt(4, infoId);
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one info request updated, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully updated info request " + infoId);
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return infoRequest;
	}

	@Override
	public void deleteRequest(InformationRequest infoRequest) {
		
		String sql = "delete from info_req where info_id = ?";
		
		log.info("Starting to delete info request with id " + infoRequest.getInfoId());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, infoRequest.getInfoId());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one info request deleted, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully deleted info request " + infoRequest.getInfoId());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
