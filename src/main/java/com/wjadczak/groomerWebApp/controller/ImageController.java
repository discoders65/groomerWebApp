package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Void> uploadImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
        imageService.uploadImage(imageFile);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping
    public ResponseEntity<byte[]> downloadImage(){
        ImageDto image = imageService.downloadCurrentUserImage();
        return  ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + image.getName() + "\"")
                .body(image.getImageData());
    }

}
