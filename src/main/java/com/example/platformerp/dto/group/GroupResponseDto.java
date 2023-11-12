package com.example.platformerp.dto.group;

import com.example.platformerp.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupResponseDto {
    private String name;
    private UUID curator;
    private Set<UserResponseDto> students;
}
