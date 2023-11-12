package com.example.platformerp.model;

import com.example.platformerp.enums.Permissions;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Entity(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRole extends BaseEntity{
    private String name;
    @Enumerated(EnumType.STRING)
    private List<Permissions> permissions;
}
