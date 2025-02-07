package com.tcs.service;

import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

public interface BucketService {

    Bucket findBucketByName(String bucketName);
    List<Bucket> findAll();
    Bucket create(String bucketName);
}
