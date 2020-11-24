/**
 * 
 */
package TRMS.dao;

import java.util.List;

import TRMS.models.Attachment;

/**
 * @author Zachary Leonardo
 *
 */
public interface AttachmentDao {

	public void createAttachment(Attachment attachment);
	
	public Attachment readAttachment(int attachId);
	
	public List<Attachment> readAllAttachments();
	
	public Attachment updateAttachment(int attachId, Attachment attachment);
	
	public void deleteAttachment(Attachment attachment);
	
}
