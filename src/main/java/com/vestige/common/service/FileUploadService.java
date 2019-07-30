package com.vestige.common.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.vestige.core.enumeration.FileType;

public interface FileUploadService {
	public String uploadFile(MultipartFile file, String id, FileType fileType) throws IOException;
}
