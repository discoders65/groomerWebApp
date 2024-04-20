package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.dto.UserDto;
import com.wjadczak.groomerWebApp.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "admin-controller", description = "Can be accessed only by admin")
public class AdminController {

    private final AdminService adminService;
    @Operation(
            summary = "Get image by url-provided Id",
            description = "Image must exist",
            parameters = {@Parameter(
                    name = "imageId",
                    description = "Existing image Id",
                    example = "548ac69c-a182-4a5d-9720-154e356a3d3f"
            )},
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.IMAGE_JPEG_VALUE
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid Id provided",
                            responseCode = "404"
                    )

            }
    )
    @GetMapping("/image/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@RequestParam UUID imageId) {
        ImageDto image = adminService.downloadImage(imageId);
        return  ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + image.getName() + "\"")
                .body(image.getImageData());
    }

    @Operation(
            summary = "Confirms appointment by url-provided Id",
            description = "Appointment must exist",
            parameters = {@Parameter(
                    name = "imageId",
                    description = "Existing appointment Id",
                    example = "548ac69c-a182-4a5d-9720-154e356a3d3f"
            )},
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid Id provided",
                            responseCode = "404"
                    )

            }
    )
    @PutMapping("/appointment")
    public ResponseEntity<Void> confirmAppointment(@RequestParam UUID appointmentId) {
        adminService.confirmAppointment(appointmentId);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Gets all users from database",
            description = "Returns nothing if no user found in database",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UserDto.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.getAllUsers());
    }

    @Operation(
            summary = "Gets user by url-provided Id",
            description = "User must exist",
            parameters = {@Parameter(
                    name = "imageId",
                    description = "Existing user Id",
                    example = "548ac69c-a182-4a5d-9720-154e356a3d3f"
            )},
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UserDto.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid Id provided",
                            responseCode = "404",
                            content = @Content
                    )
            }
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUser(@RequestParam UUID userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.getUserById(userId));
    }

    @Operation(
            summary = "Cancels appointment by url-provided Id",
            description = "Appointment must exist",
            parameters = {@Parameter(
                    name = "imageId",
                    description = "Existing appointment Id",
                    example = "548ac69c-a182-4a5d-9720-154e356a3d3f"
            )},
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid Id provided",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/appointment")
    public ResponseEntity<Void> cancelAppointment(@RequestParam UUID appointmentId) {
        adminService.cancelAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
