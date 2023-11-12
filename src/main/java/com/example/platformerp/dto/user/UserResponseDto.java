package com.example.platformerp.dto.user;

import com.example.platformerp.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private UserType userType;
    private String phoneNumber;
    private String email;

}
