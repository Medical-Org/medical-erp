package com.example.platformerp.controller;

import com.example.platformerp.dto.JwtResponseDto;
import com.example.platformerp.dto.LoginDto;
import com.example.platformerp.dto.user.UserCreatedDto;
import com.example.platformerp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    public static final String BASE_PATH="/api/v1/auth";

   /* @PreAuthorize("hasAnyAuthority('STUDENT_CREATE','ADMIN_CREATE','MANAGER_CREATE')")*/
    @PostMapping("/register")
    public ResponseEntity<JwtResponseDto> signUp(@Valid @RequestBody UserCreatedDto user){
        return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> signIn(@Valid @RequestBody LoginDto loginDto){
       return ResponseEntity.ok(userService.login(loginDto));
    }
}
