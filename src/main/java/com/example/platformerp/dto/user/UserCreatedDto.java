package com.example.platformerp.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreatedDto {
    @NotBlank(message = "FirstName must not be null or empty")
    private String firstName;
    @NotBlank(message = "LastName must not be null or empty")
    private String lastName;
    private String middleName;
    @NotNull(message = "User role must not be null")
    private Set<String> roles;
    @Pattern(regexp = "^998(99|88|33|94|95|71|90|93)\\d{7}$", message = "Incorrect uzbek phone Number")
    @NotBlank(message = "Phone number must be not nul or empty")
    private String phoneNumber;
    @NotBlank(message = "Phone number must be not nul or empty")
    private String email;
    @NotBlank(message = "Password must be not null or emty ")
    @Size(min = 6, max = 20, message = "Password min size = {min} and max size = {max}")
    private String password;
    @NotBlank(message = "Confirm password must be not null or emty ")
    private String confirmPassword;
}
