package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.entity.ImageEntity;
import com.wjadczak.groomerWebApp.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
        imageService.uploadImage(imageFile);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping("download/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable UUID imageId) {
        ImageEntity image = imageService.downloadImage(imageId);
        return  ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + image.getName() + "\"")
                .body(image.getImageData());
    }
}
