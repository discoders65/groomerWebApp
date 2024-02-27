package com.wjadczak.groomerWebApp.controller;


import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSaveRequestDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.dto.CancelAppointmentDto;
import com.wjadczak.groomerWebApp.errors.AppointmentNotFoundException;
import com.wjadczak.groomerWebApp.errors.InvalidSaveAppointmentDataInputException;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calendar")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Operation(
            summary = "Returns list of appointments found between provided date-time input",
            description = "Returns nothing if no appointment found",
            parameters = {
                    @Parameter(
                            name = "appointmentSearchRequestDto",
                            description = "Appointment search request object",
                            required = true,
                            content = @Content(
                                    schema = @Schema(implementation = AppointmentSearchRequestDto.class)
                            )
                    )
            },
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = AppointmentDto.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid or null date-time input provided",
                            responseCode = "400",
                            content = @Content
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> findAppointment(@RequestBody AppointmentSearchRequestDto appointmentSearchRequestDto) throws InvalidSearchRequestException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appointmentService
                        .findAppointment(appointmentSearchRequestDto));
    }

    @Operation(
            summary = "Saves appointment defined by provided date-time input",
            description = "Appointment must exist",
            parameters = @Parameter(
                    name = "appointmentSaveRequestDto",
                    description = "Appointment save request object",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = AppointmentSaveRequestDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid or null date-time input",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveAppointment(@RequestBody AppointmentSaveRequestDto appointmentSaveRequestDto) throws InvalidSaveAppointmentDataInputException {
        appointmentService.saveAppointment(appointmentSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(
            summary = "Cancels appointment by defined provided date-time input",
            description = "Appointment must exist & must be owned by currently logged user",
            parameters = @Parameter(
                    name = "cancelAppointmentDto",
                    description = "Appointment cancel request object",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CancelAppointmentDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Invalid Id provided",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping
    public ResponseEntity<Void> cancelAppointment(@RequestBody CancelAppointmentDto cancelAppointmentDto) throws AppointmentNotFoundException {
        appointmentService.cancelCurrentUserAppointment(cancelAppointmentDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

}
