package com.example.platformerp.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupRequestDto {
    @NonNull
    private String name;
    @NonNull
    private UUID curator;
}
