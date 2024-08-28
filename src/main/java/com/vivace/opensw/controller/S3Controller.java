package com.vivace.opensw.controller;

import com.vivace.opensw.global.S3.S3ImageService;
import com.vivace.opensw.service.ImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3ImageService s3ImageService;
    private final ImgService imgService;

    @PostMapping("/s3/upload")
    public ResponseEntity<?> s3Upload(@RequestPart(value = "image", required = false) MultipartFile image){
        String profileImage = s3ImageService.upload(image);
        return ResponseEntity.ok(profileImage);
    }

    @GetMapping("/s3/delete")
    public ResponseEntity<?> s3delete(@RequestParam String addr){
        s3ImageService.deleteImageFromS3(addr);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/s3/upload/{artifactId}")
    public ResponseEntity<String> artifactImgUpload(@RequestPart(value = "image", required = false) MultipartFile image,@PathVariable Long artifactId){
        String profileImage = s3ImageService.upload(image);
        imgService.save(profileImage,artifactId);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileImage);
    }

}
