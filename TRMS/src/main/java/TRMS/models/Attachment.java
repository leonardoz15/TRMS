/**
 * 
 */
package TRMS.models;

import java.io.InputStream;

/**
 * @author Zachary Leonardo
 *
 */
public class Attachment {

	private int attachId;
	
	private int requestId;
	
	private String fileType;
	
	private InputStream dataStream;

	
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
	public Attachment(int attachId, int requestId, String fileType, InputStream dataStream) {
		super();
		this.attachId = attachId;
		this.requestId = requestId;
		this.fileType = fileType;
		this.dataStream = dataStream;
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
	 * @return the dataStream
	 */
	public InputStream getDataStream() {
		return dataStream;
	}


	/**
	 * @param dataStream the dataStream to set
	 */
	public void setDataStream(InputStream dataStream) {
		this.dataStream = dataStream;
	}


	@Override
	public String toString() {
		return "Attachment [attachId=" + attachId + ", requestId=" + requestId + ", fileType=" + fileType
				+ ", dataStream=" + dataStream + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attachId;
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
