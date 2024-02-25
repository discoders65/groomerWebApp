package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.dto.UserDto;
import com.wjadczak.groomerWebApp.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @Operation(
            summary = "Get image by url-provided Id",
            description = "Image must exist",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "byte[]"
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid Id provided",
                            responseCode = "400"
                    )

            }
    )
    @GetMapping("/image/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable UUID imageId) {
        ImageDto image = adminService.downloadImage(imageId);
        return  ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + image.getName() + "\"")
                .body(image.getImageData());
    }

    @PutMapping("/confirm/{appointmentId}")
    public ResponseEntity<Void> confirmAppointment(@PathVariable UUID appointmentId) {
        adminService.confirmAppointment(appointmentId);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.getAllUsers());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable  UUID userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.getUserById(userId));
    }

}
