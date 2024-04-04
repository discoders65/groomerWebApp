package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
            parameters = {
                    @Parameter(
                            name = "image",
                            description = "Image file to upload",
                            required = true,
                            content = @Content(
                                    mediaType = "multipart/form-data"
                            )
                    )
            },
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Currently logged user already uploaded an image OR Null image-file input OR Image-file larger than 10mb",
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
                                    mediaType = MediaType.IMAGE_JPEG_VALUE
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDispositionFormData("attachment", image.getName());

        return  ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(image.getImageData());
    }

}
