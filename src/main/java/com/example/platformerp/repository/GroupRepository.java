package com.example.platformerp.repository;

import com.example.platformerp.model.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {
}
