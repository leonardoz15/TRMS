/**
 * 
 */
package TRMS.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.Attachment;
import TRMS.models.User;
import TRMS.models.User.AuthPriv;
import TRMS.util.ConnectionUtil;

/**
 * @author Zachary Leonardo
 *
 */
public class AttachmentDaoPostgres implements AttachmentDao {
	
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
	public void createAttachment(Attachment attachment) {
		
		String sql = "insert into attachment values (default, ?, ?, ?)";
		
		log.info("Starting to insert attachment with id " + attachment.getAttachId());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, attachment.getRequestId());
			stmt.setString(2, attachment.getFileType());
			stmt.setBytes(3, attachment.getFile());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one attachment created, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully inserted attachment " + attachment.getAttachId());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Attachment readAttachment(int attachId) {
		
		Attachment read = null;
		
		String sql = "select * from attachment where attach_id = ?";
		
		log.info("Starting to read attachment with id " + attachId);
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, attachId);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				read = new Attachment(rs.getInt(1), rs.getInt(2), rs.getString(3));
				read.setFile(rs.getBinaryStream(4).readAllBytes());
			}
			log.info("Successfully read attachment " + read.getAttachId());
			
			return read;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading attachment with id " + attachId);
			e.printStackTrace();
		} catch (IOException e) {
			log.warn("IOException thrown when reading attachment with id " + attachId);
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<Attachment> readAllAttachments() {
		
		List<Attachment> result = new ArrayList<Attachment>();
		
		String sql = "select * from attachment";
		
		log.info("Starting to read all attachments");
		
		try (Connection conn = connUtil.createConnection()){
			
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Attachment toAdd = new Attachment(rs.getInt(1), rs.getInt(2), rs.getString(3));
				toAdd.setFile(rs.getBinaryStream(4).readAllBytes());
				
				result.add(toAdd);
			}
			log.info("Successfully read all attachments, total: " + result.size());
			
			return result;
			
		} catch (SQLException e) {
			log.warn("SQLException thrown when reading all attachments");
			e.printStackTrace();
		} catch (IOException e) {
			log.warn("IOException thrown when reading all attachments with id ");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Attachment updateAttachment(int attachId, Attachment attachment) {
		
		String sql = "update attachment set request_id = ?, file_type = ?, file = ? where attach_id = ?";
		
		log.info("Starting to update attachment with id " + attachId);
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, attachment.getRequestId());
			stmt.setString(2, attachment.getFileType());
			stmt.setBytes(3, attachment.getFile());
			stmt.setInt(4, attachId);
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one attachment updated, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully updated attachment " + attachId);
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return attachment;
	}

	@Override
	public void deleteAttachment(Attachment attachment) {
		
		String sql = "delete from attachment where attach_id = ?";
		
		log.info("Starting to delete attachment with id " + attachment.getAttachId());
		
		try(Connection conn = connUtil.createConnection()) {
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, attachment.getAttachId());
			
			Savepoint s1 = conn.setSavepoint();
			int rowsEffected = stmt.executeUpdate();
			
			if (rowsEffected != 1) {
				log.warn("More than one attachment deleted, rolling back");
				conn.rollback(s1);
			} else {
				conn.commit();
				log.info("Successfully deleted attachment " + attachment.getAttachId());
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
