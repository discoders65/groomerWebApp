package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.config.security.AuthenticationService;
import com.wjadczak.groomerWebApp.dto.JwtAuthenticationResponseDto;
import com.wjadczak.groomerWebApp.dto.SignInDto;
import com.wjadczak.groomerWebApp.dto.SignUpDto;
import io.swagger.v3.oas.annotations.Operation;
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
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Password to short or no special character provided",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Username or mail already exists",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Empty or part-empty user registration input",
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
