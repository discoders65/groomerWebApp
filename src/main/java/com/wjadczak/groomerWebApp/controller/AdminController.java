package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.dto.UserDto;
import com.wjadczak.groomerWebApp.dto.UtilityDto;
import com.wjadczak.groomerWebApp.service.AdminService;
import com.wjadczak.groomerWebApp.service.UtilityService;
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
    private final UtilityService utilityService;

    @Operation(
            summary = "Get all utilities",
            description = "Retrieves a list of all utilities available",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UtilityDto.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )

    @GetMapping("/utility")
    public ResponseEntity<List<UtilityDto>> getAllUtilities(){
        List<UtilityDto> utilities = utilityService.getAllUtilities();
        return ResponseEntity.status(HttpStatus.OK)
                .body(utilities);
    }
    @Operation(
            summary = "Get utility by Id",
            description = "Retrieves a utility based on its Id",
            parameters = {@Parameter(
                    name = "utilityId",
                    description = "Existing utility Id",
                    example = "548ac69c-a182-4a5d-9720-154e356a3d3f"
            )},
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UtilityDto.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid Id provided",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/utility/{utilityId}")
    public ResponseEntity<UtilityDto> getUtilityById(@RequestParam UUID utilityId){
        UtilityDto utility = utilityService.getUtilityById(utilityId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(utility);
    }

    @Operation(
            summary = "Add new utility",
            description = "Adds a new utility to the system",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Utility details to be added",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UtilityDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "Created successfully",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping("/utility")
    public ResponseEntity<Void> addUtility(@RequestBody UtilityDto utilityDto){
        utilityService.addUtility(utilityDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(
            summary = "Delete utility by Id",
            description = "Deletes a utility from the system",
            parameters = {@Parameter(
                    name = "utilityId",
                    description = "Existing utility Id",
                    example = "548ac69c-a182-4a5d-9720-154e356a3d3f"
            )},
            responses = {
                    @ApiResponse(
                            description = "Accepted for deletion",
                            responseCode = "202"
                    ),
                    @ApiResponse(
                            description = "Invalid Id provided",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/utility/{utilityId}")
    public ResponseEntity<Void> deleteUtility(@RequestParam UUID utilityId){
        utilityService.deleteUtility(utilityId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

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
