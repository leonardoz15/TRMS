package TRMS.services;

import java.util.List;

import org.apache.log4j.Logger;

import TRMS.dao.AttachmentDao;
import TRMS.dao.AttachmentDaoPostgres;
import TRMS.models.Attachment;

public class AttachmentServiceFullStack implements AttachmentService {
	
	private AttachmentDao attachmentDao = new AttachmentDaoPostgres();
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public void createAttachment(Attachment attachment) {
		
		log.info("Attachment service creating attachment");
		
		attachmentDao.createAttachment(attachment);
	}

	@Override
	public Attachment readAttachment(int attachId) {

		log.info("Attachment service reading attachment");
		
		return attachmentDao.readAttachment(attachId);
	}

	@Override
	public List<Attachment> readAllAttachments() {

		log.info("Attachment service reading all attachments");
		
		return attachmentDao.readAllAttachments();
	}

	@Override
	public Attachment updateAttachment(int attachId, Attachment attachment) {
		
		log.info("Attachment service updating attachment");
		
		return attachmentDao.updateAttachment(attachId, attachment);
	}

	@Override
	public void deleteAttachment(Attachment attachment) {
		
		log.info("Attachment service deleting attachment");
		
		attachmentDao.deleteAttachment(attachment);
	}

}
