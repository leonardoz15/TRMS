/**
 * 
 */
package TRMS.models;

import java.io.InputStream;
import java.util.Arrays;

/**
 * @author Zachary Leonardo
 *
 */
public class Attachment {

	private int attachId;
	
	private int requestId;
	
	private String fileType;
	
	private byte[] file;

	
	public Attachment() {
		super();
	}


	/** Without data
	 * @param attachId the unique id of this attachment
	 * @param requestId the id of the request this attachment belongs to
	 * @param fileType the type of file this attachment is
	 */
	public Attachment(int attachId, int requestId, String fileType) {
		super();
		this.attachId = attachId;
		this.requestId = requestId;
		this.fileType = fileType;
	}


	/** With data
	 * @param attachId
	 * @param requestId
	 * @param fileType
	 * @param dataStream
	 */
	public Attachment(int attachId, int requestId, String fileType, byte[] file) {
		super();
		this.attachId = attachId;
		this.requestId = requestId;
		this.fileType = fileType;
		this.file = file;
	}


	/**
	 * @return the attachId
	 */
	public int getAttachId() {
		return attachId;
	}


	/**
	 * @param attachId the attachId to set
	 */
	public void setAttachId(int attachId) {
		this.attachId = attachId;
	}


	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}


	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}


	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}


	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	/**
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}


	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}


	@Override
	public String toString() {
		return "Attachment [attachId=" + attachId + ", requestId=" + requestId + ", fileType=" + fileType + ", file="
				+ Arrays.toString(file) + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attachId;
		result = prime * result + Arrays.hashCode(file);
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
		result = prime * result + requestId;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attachment other = (Attachment) obj;
		if (attachId != other.attachId)
			return false;
		if (!Arrays.equals(file, other.file))
			return false;
		if (fileType == null) {
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		if (requestId != other.requestId)
			return false;
		return true;
	}
	
}
