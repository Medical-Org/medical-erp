package com.example.platformerp.service.validator;

import com.example.platformerp.model.SubjectEntity;
import com.example.platformerp.repository.SubjectRepository;
import org.springframework.stereotype.Component;

@Component
public class SubjectValidator extends AbstractValidator<SubjectEntity, SubjectRepository> {
}
