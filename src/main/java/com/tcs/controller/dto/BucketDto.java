package com.tcs.controller.dto;

import com.amazonaws.services.s3.model.Bucket;

public record BucketDto(String name) {

    public BucketDto(Bucket model) {
        this(model.getName());
    }

//    public Bucket toModel() {
//        Bucket model = new Bucket();
//        model.setName(this.name);
//        return model;
//    }

}
