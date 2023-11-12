package com.example.platformerp.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "groups")
public class GroupEntity extends BaseEntity {
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private UserEntity curator;
    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER)
    List<UserEntity> students;
}
