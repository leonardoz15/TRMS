package TRMS.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import TRMS.models.Attachment;
import TRMS.services.AttachmentService;
import TRMS.services.AttachmentServiceFullStack;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

public class AttachmentController {
	
	private static Logger log = Logger.getRootLogger();
	
	private AttachmentService service = new AttachmentServiceFullStack();
	
	public void createAttachment(Context ctx) {
		
		try {
			
			int requestId = Integer.parseInt(ctx.formParam("request_id"));
			String fileType = ctx.formParam("file_type");
			UploadedFile file = ctx.uploadedFile("file");
			
			Attachment toCreate = new Attachment(0, requestId, fileType, file.getContent().readAllBytes());
			
			service.createAttachment(toCreate);			
			
			log.info("Successfully created attachment for request: " + requestId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when creating attachment: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void readAttachment(Context ctx) {
		
		try {
			
			int attachId = Integer.parseInt(ctx.formParam("attach_id"));
			
			ctx.json(service.readAttachment(attachId));
			
			log.info("Successfully read attachment: " + attachId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading attachment: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void readAllAttachments(Context ctx) {
		
		try {
			
			List<Attachment> attachList = new ArrayList<Attachment>();
			
			attachList = service.readAllAttachments();
			
			ctx.json(attachList);
			
			log.info("Successfully read all attachments");
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when reading all attachments: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void updateAttachment(Context ctx) {
		
		try {
			
			int attachId = Integer.parseInt(ctx.formParam("attach_id"));
			
			int requestId = Integer.parseInt(ctx.formParam("request_id"));
			String fileType = ctx.formParam("file_type");
			UploadedFile file = ctx.uploadedFile("file");
			
			Attachment toUpdate = new Attachment(0, requestId, fileType, file.getContent().readAllBytes());
			
			service.updateAttachment(attachId, toUpdate);
			
			log.info("Successfully updated attachment: " + attachId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when updating attachment: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
	public void deleteAttachment(Context ctx) {
		
		try {
			
			int attachId = Integer.parseInt(ctx.formParam("attach_id"));
			
			int requestId = Integer.parseInt(ctx.formParam("request_id"));
			String fileType = ctx.formParam("file_type");
			UploadedFile file = ctx.uploadedFile("file");
			
			Attachment toDelete = new Attachment(attachId, requestId, fileType, file.getContent().readAllBytes());
			
			service.deleteAttachment(toDelete);
			
			log.info("Successfully deleted attachment: " + attachId);
			ctx.status(200);
			
		} catch (Exception e) {
			log.warn("Exception thrown when deleting attachment: " + e);
			ctx.html("Exception " + e);
			ctx.status(500);
		}
	}
	
}
