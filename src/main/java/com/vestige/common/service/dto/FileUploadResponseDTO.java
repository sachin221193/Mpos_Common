package com.vestige.common.service.dto;

/**
 * 
 * @author tarsem.kumar
 * @description Return response after imges upload
 *
 */

public class FileUploadResponseDTO {
	
	private String fileName;
    private String fileUploadUri;
    private String message;
    
    public FileUploadResponseDTO(String fileName, String fileUploadUri,String message) {
        this.fileName = fileName;
        this.fileUploadUri = fileUploadUri;
        this.message = message;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUploadUri() {
		return fileUploadUri;
	}

	public void setFileUploadUri(String fileUploadUri) {
		this.fileUploadUri = fileUploadUri;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}