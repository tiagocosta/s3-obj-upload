package com.tcs.service;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public interface ObjectService {

    List<S3ObjectSummary> findBucketObjects(String bucketName);

    void uploadObject(String bucketName, File file);
}
