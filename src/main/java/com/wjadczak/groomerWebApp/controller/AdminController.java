package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.dto.UserDto;
import com.wjadczak.groomerWebApp.service.AdminService;
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
    @GetMapping("/download/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable UUID imageId) {
        ImageDto image = adminService.downloadImage(imageId);
        return  ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + image.getName() + "\"")
                .body(image.getImageData());
    }

    @PutMapping("/appointment/confirm/{appointmentId}")
    public ResponseEntity<Void> confirmAppointment(@PathVariable UUID appointmentId) {
        adminService.confirmAppointment(appointmentId);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/users")
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
