package com.example.platformerp.dto.subject;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectRequestDto {
    @NotBlank(message = "subject name must be not null or empty")
    private String name;
}
