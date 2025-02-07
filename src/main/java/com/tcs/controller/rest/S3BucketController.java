package com.tcs.controller.rest;

import com.tcs.controller.dto.BucketDto;
import com.tcs.service.BucketService;
import com.tcs.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/s3/bucket")
public class S3BucketController {

    @Autowired
    private BucketService bucketService;

    @GetMapping
    public ResponseEntity<List<BucketDto>> findAll() {
        var buckets = bucketService.findAll();
        var bucketsDto = buckets.stream().map(BucketDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(bucketsDto);
    }

    @GetMapping("/{bucketName}")
    public ResponseEntity<BucketDto> findByName(@PathVariable String bucketName) {
        var bucket = bucketService.findBucketByName(bucketName);
        return ResponseEntity.ok(new BucketDto(bucket));
    }
}
