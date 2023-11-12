package com.example.platformerp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    @NotBlank(message = "Email must be not nul or empty")
    private String email;
    @NotBlank(message = "Password must be not null or empty")
    private String password;
}
