package com.example.platformerp.dto.subject;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectResponseDto {
    private UUID id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
