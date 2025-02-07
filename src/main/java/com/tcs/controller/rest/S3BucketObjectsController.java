package com.tcs.controller.rest;

import com.tcs.controller.dto.BucketDto;
import com.tcs.controller.dto.BucketObjectDto;
import com.tcs.service.BucketService;
import com.tcs.service.ObjectService;
import com.tcs.service.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/s3/buckets")
public class S3BucketObjectsController {

    @Autowired
    private ObjectService objectService;

    @Value("${temp.folder}")
    private String tmpFolder;

    @GetMapping("/{bucketName}/objects")
    public ResponseEntity<List<BucketObjectDto>> findAll(@PathVariable String bucketName) {
        var objects = objectService.findBucketObjects(bucketName);
        var objectsDto = objects.stream().map(BucketObjectDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(objectsDto);
    }

    @PostMapping("/{bucketName}/objects")
    public String handleFileUpload(
            @PathVariable String bucketName,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) {
        try {
            File fileToStore = new File(tmpFolder + "/" + file.getOriginalFilename());
            Files.copy(file.getInputStream(), fileToStore.toPath());
            objectService.uploadObject(bucketName, fileToStore);
            fileToStore.delete();
        } catch (IOException e) {
            throw new FileUploadException();
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
}
