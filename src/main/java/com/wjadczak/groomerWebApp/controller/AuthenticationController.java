package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.config.security.AuthenticationService;
import com.wjadczak.groomerWebApp.dto.JwtAuthenticationResponseDto;
import com.wjadczak.groomerWebApp.dto.SignInDto;
import com.wjadczak.groomerWebApp.dto.SignUpDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Saves user to database",
            description = "Username and email must be unique, password must have min. 8 characters and contain at least one special character: !@#$%^&*()_+=-?|:;'.,/<>][{}",
            parameters = {
                    @Parameter(
                            name = "signUpDto",
                            description = "User registration information",
                            required = true,
                            content = @Content(
                                    schema = @Schema(implementation = SignUpDto.class)
                            )
                    )
            },
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Password to short or no special character provided OR" +
                                    " Username or mail already exists OR" +
                                    " Copy string literal text to the clipboard",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        authenticationService.signUp(signUpDto);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Operation(
            summary = "User login",
            description = "Returns JWT token, valid for 30min",
            parameters = {
                    @Parameter(
                            name = "signInDto",
                            description = "User sign-in information",
                            required = true,
                            content = @Content(
                                    schema = @Schema(implementation = SignInDto.class)
                            )
                    )
            },
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid username or mail",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDto> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(authenticationService.signIn(signInDto));
    }
}
