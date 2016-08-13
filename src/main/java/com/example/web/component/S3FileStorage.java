package com.example.web.component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.web.model.FileData;
import com.example.web.util.Constants;

@Component
public class S3FileStorage implements FileStorage {
	
	private final AWSCredentials awsCredentials;
	private final String bucketName;

	@Autowired
    public S3FileStorage(Environment env) {
		bucketName = env.getProperty(Constants.AWS_S3_BUCKET);
		awsCredentials = new BasicAWSCredentials(env.getProperty(Constants.AWS_ACCESS_KEY), env.getProperty(Constants.AWS_SECRET_KEY));
	}

    
	public String storeFile(FileData file) {
        ClientConfiguration cf = new ClientConfiguration();
        cf.setMaxErrorRetry(5);
        AmazonS3 s3 = new AmazonS3Client(awsCredentials, cf);
        Region region = Region.getRegion(Regions.AP_SOUTHEAST_1);
        s3.setRegion(region);

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(file.getBytes().length);
        meta.setContentType(file.getContentType());

        InputStream stream = new ByteArrayInputStream(file.getBytes());

        PutObjectResult putObjectResult = s3.putObject(new PutObjectRequest(bucketName, (file.getFolderPath() != null ? file.getFolderPath() + "/" : "") + file.getName(), stream, meta));
        return putObjectResult.getETag();
    }
}
