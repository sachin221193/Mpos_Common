package com.vestige.common.web.rest;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vestige.common.service.FileUploadService;
import com.vestige.common.service.dto.FileUploadResponseDTO;
import com.vestige.core.enumeration.FileType;
import com.vestige.core.enumeration.Message;
import com.vestige.core.exceptions.BadRequestAlertException;


@RestController
@RequestMapping("/api/" + "${application.api.version}")
public class FileUploadResource {

	private final Logger log = LoggerFactory.getLogger(LanguageResource.class);
	
	private static final String ENTITY_NAME = "fileUpload";

	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping("/file-upload")
	public ResponseEntity<FileUploadResponseDTO> uploadFile(@RequestParam(name="file", required=false) MultipartFile file ,
    		@RequestParam(name="filetype", required=true) String fileType) {
		FileType uploadType = FileType.getFileTypeByCode(fileType);
		if(java.util.Objects.isNull(file) || java.util.Objects.isNull(uploadType)){
			throw new BadRequestAlertException(Message.MONDATORY_FIELD_MISSING.getMessage(), ENTITY_NAME, "file");
		}
		log.info("{}",file.getSize());
		if(file.getSize() < com.vestige.common.config.Constants.FILE_MIN_SIZE){
			throw new BadRequestAlertException(Message.UPLOAD_FILE_REPORT_MIN_SIZE_EXCEPTION.getMessage(), ENTITY_NAME, "file");
		}else if(FileType.IMAGES == uploadType || FileType.VIDEOS == uploadType){
			if(file.getSize() > com.vestige.common.config.Constants.FILE_MAX_SIZE){
				throw new BadRequestAlertException(Message.UPLOAD_FILE_REPORT_MAX_SIZE_EXCEPTION.getMessage(), ENTITY_NAME, "file");
			}
		}
		String uploadedPath = null;
		try {
			uploadedPath = fileUploadService.uploadFile(file,UUID.randomUUID().toString(),FileType.getFileTypeByCode(fileType));
			log.debug("Doc upload completed, Location: {}" , uploadedPath);
		} catch (Exception exception) {
			log.error("Exception in uploadMultipleFileHandler : {} " , exception.getMessage());
			log.debug("{}" , exception);
			throw new BadRequestAlertException(Message.SUCCESSFULLY_NOT_UPLOAD.getMessage(), ENTITY_NAME, "file");
		}
		return new ResponseEntity<>(
				new FileUploadResponseDTO(file.getOriginalFilename(), uploadedPath,
						Message.SUCCESSFULLY_UPLOAD.getMessage()),
				HttpStatus.OK);
	}
}
