package com.tcs.controller.rest;

import com.tcs.controller.dto.BucketDto;
import com.tcs.controller.dto.BucketObjectDto;
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
@RequestMapping("/s3/buckets")
public class S3BucketObjectsController {

    @Autowired
    private ObjectService objectService;

    @GetMapping("/{bucketName}/objects")
    public ResponseEntity<List<BucketObjectDto>> findAll(@PathVariable String bucketName) {
        var objects = objectService.findBucketObjects(bucketName);
        var objectsDto = objects.stream().map(BucketObjectDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(objectsDto);
    }

//    @GetMapping("/{bucketName}")
//    public ResponseEntity<BucketDto> findByName(@PathVariable String bucketName) {
//        var bucket = bucketService.findBucketByName(bucketName);
//        return ResponseEntity.ok(new BucketDto(bucket));
//    }
}
