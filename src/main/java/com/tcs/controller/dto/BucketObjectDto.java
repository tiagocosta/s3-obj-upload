package com.tcs.controller.dto;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.Owner;
import com.amazonaws.services.s3.model.RestoreStatus;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public record BucketObjectDto(
        String bucketName,
        String key,
        String eTag,
        long size,
        LocalDate lastModified
) {

    public BucketObjectDto(S3ObjectSummary model) {
        this(
                model.getBucketName(),
                model.getKey(),
                model.getETag(),
                model.getSize(),
                model.getLastModified()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
        );
    }
}
