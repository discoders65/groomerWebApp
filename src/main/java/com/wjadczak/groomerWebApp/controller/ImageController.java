package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Saves image provided in form of multipart-file (user can own only one in database)",
            description = "Image file cannot be larger than 10mb",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Currently logged user already uploaded an image.",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Null image-file input",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Image-file larger than 10mb",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Void> uploadImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
        imageService.uploadImage(imageFile);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @Operation(
            summary = "Get image owned by current user (user can own only one in database)",
            description = "Returns image in form of byte array if found in database",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "byte[]"
                            )
                    ),
                    @ApiResponse(
                            description = "Currently logged user do not own image yet.",
                            responseCode = "404"
                    )

            }
    )
    @GetMapping
    public ResponseEntity<byte[]> downloadImage(){
        ImageDto image = imageService.downloadCurrentUserImage();
        return  ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + image.getName() + "\"")
                .body(image.getImageData());
    }

}
