package com.tcs.controller.rest;

import com.tcs.controller.dto.BucketDto;
import com.tcs.service.BucketService;
import com.tcs.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<BucketDto> create(@RequestBody BucketDto bucketDto) {
        var bucket = bucketService.create(bucketDto.name());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{bucketName}")
                .buildAndExpand(bucket.getName())
                .toUri();
        return ResponseEntity.created(location).body(new BucketDto(bucket));
    }
}
