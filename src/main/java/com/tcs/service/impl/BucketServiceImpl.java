package com.tcs.service.impl;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListBucketsPaginatedRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.tcs.aws.AwsConfig;
import com.tcs.service.BucketService;
import com.tcs.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BucketServiceImpl implements BucketService {

    @Autowired
    private AwsConfig awsConfig;

    @Override
    public Bucket findBucketByName(String name) {
        final var s3 = awsConfig.s3();
        final var buckets = s3.listBuckets(new ListBucketsPaginatedRequest());
        Optional<Bucket> bucket = buckets.getBuckets().stream()
                .filter(b -> b.getName().equals(name))
                .findFirst();
        return bucket.orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Bucket> findAll() {
        final var s3 = awsConfig.s3();
        return s3.listBuckets(new ListBucketsPaginatedRequest()).getBuckets();
    }
}
