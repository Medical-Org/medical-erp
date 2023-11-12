package com.example.platformerp.repository;

import com.example.platformerp.model.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, UUID> {
}
