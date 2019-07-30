package com.vestige.common.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.vestige.common.service.FileUploadService;
import com.vestige.core.enumeration.FileType;
import com.vestige.core.exceptions.BadRequestAlertException;

@Service
public class FileUploadServiceImpl implements FileUploadService{

	private final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);
	
	@Value("${application.fileupload.secret_id}")
	private String secretId;
	
	@Value("${application.fileupload.secret_key}")
	private String secretKey;
	
	@Value("${application.fileupload.region_name}")
	private String regionName;
	
	@Value("${application.fileupload.bucket_name}")
	private String accessName;
	
	@Value("${application.fileupload.temp_path}")
	private String tempPath;

	
	@Override
	public String uploadFile(MultipartFile file, String id, FileType fileType) throws IOException {
		File serverFile = null;
		String uploadedPath = null;
		BufferedOutputStream stream = null;
		File dir = null;
		String name = file.getOriginalFilename();
		try {
			dir = new File(tempPath + File.separator + id);
			if (!dir.exists())
				dir.mkdirs();
			byte[] bytes = file.getBytes();
			// Create the temp file on server
			serverFile = new File(dir.getAbsolutePath() + File.separator + name.replace(" ", ""));
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			uploadedPath = uploadFileS3BucketByMultipart(id, serverFile, fileType);
			log.info("Doc upload completed, Location: {}", serverFile.getAbsolutePath());
		} catch (Exception e) {
			log.error("Exception in upload Multiple File : {}", e.getMessage());
			log.debug("{}", e);
			throw new BadRequestAlertException(e.getMessage());
		} finally {
			if (stream != null)
				stream.close();
		}	
		return uploadedPath;
	}
	
	/**
	 * 
	 * @param keyName
	 * @param serverFile
	 * @param fileType
	 * @return
	 * @throws UploadFileException
	 */

	private String uploadFileS3BucketByMultipart(String keyName, File serverFile, FileType fileType) {
		String nameOfKey = null;
		UploadPartRequest uploadRequest = null;
		try {
			String bucketName = accessName + "/" + fileType.getName() + "/" + keyName;
			String serverFileName = serverFile.getName();
			nameOfKey = bucketName + "/" + serverFileName;
			InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName,
					serverFileName);
			InitiateMultipartUploadResult initResponse = getS3Credentials().initiateMultipartUpload(initRequest);
			Long contentLength = serverFile.length();
			long partSize = 5242880; // Set part size to 5MB.
			long filePosition = 0;
			List<PartETag> partETags = new ArrayList<PartETag>(contentLength.intValue());
			for (int i = 1; filePosition < contentLength; i++) {
				partSize = Math.min(partSize, (contentLength - filePosition));
				uploadRequest = new UploadPartRequest().withBucketName(bucketName)
						.withKey(serverFile.getName()).withUploadId(initResponse.getUploadId()).withPartNumber(i)
						.withFileOffset(filePosition).withFile(serverFile).withPartSize(partSize);
				partETags.add(getS3Credentials().uploadPart(uploadRequest).getPartETag());
				filePosition += partSize;
			}
			CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName,
					serverFile.getName(), initResponse.getUploadId(), partETags);

			getS3Credentials().completeMultipartUpload(compRequest);
		} catch (AmazonServiceException ase) {
			log.error(
					"Caught an AmazonServiceException, which means your request made it to Amazon S3, but was rejected with an error response"
							+ " for some reason.");
			log.error("Error Message:   {} ", ase.getMessage());
			log.error("HTTP Status Code: {}", ase.getStatusCode());
			log.error("AWS Error Code:  {} ", ase.getErrorCode());
			log.error("Error Type:      {} ", ase.getErrorType());
			log.error("Request ID:      {} ", ase.getRequestId());
			throw new BadRequestAlertException(ase.getMessage());
		} catch (AmazonClientException ace) {
			log.error(
					"Caught an AmazonClientException, which means the client encountered an internal error while trying to communicate with S3, "
							+ "such as not being able to access the network.");
			log.error("Error Message:{} ", ace.getMessage());
			throw new BadRequestAlertException(ace.getMessage());
		} catch (Exception e) {
			log.error("Error Message: {}", e.getMessage());
			throw new BadRequestAlertException(e.getMessage());
		}
		StringBuilder uploadedFileUrl = new StringBuilder("https://s3."+regionName+".amazonaws.com");
		uploadedFileUrl.append('/').append(nameOfKey);

		return uploadedFileUrl.toString();
	}
	
	private AmazonS3 getS3Credentials() {
		AWSCredentials credentials = new BasicAWSCredentials(secretId, secretKey);
		ClientConfiguration config = new ClientConfiguration();
		config.setProtocol(Protocol.HTTP);
		AmazonS3 s3Client = new AmazonS3Client(credentials, config);
		return s3Client;
	}
}
