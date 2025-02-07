package com.tcs.service.impl;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.tcs.aws.AwsConfig;
import com.tcs.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    private AwsConfig awsConfig;

    @Override
    public List<S3ObjectSummary> findBucketObjects(String bucketName) {
        final var s3 = awsConfig.s3();
        return s3.listObjectsV2(bucketName).getObjectSummaries();
    }

    @Override
    public void uploadObject(String bucketName, File file) {
        final var s3 = awsConfig.s3();
        s3.putObject(bucketName, file.getName(), file);
    }
}
