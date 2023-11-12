package com.example.platformerp.service;

import com.example.platformerp.dto.subject.SubjectRequestDto;
import com.example.platformerp.dto.subject.SubjectResponseDto;
import com.example.platformerp.model.SubjectEntity;
import com.example.platformerp.repository.SubjectRepository;
import com.example.platformerp.service.base.BaseService;
import com.example.platformerp.service.validator.SubjectValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubjectService extends BaseService<
        SubjectEntity,
        UUID,
        SubjectRepository,
        SubjectRequestDto,
        SubjectResponseDto,
        SubjectValidator> {


    public SubjectService(SubjectRepository repository, SubjectValidator validator, ModelMapper modelMapper) {
        super(repository, validator, modelMapper);
    }

    @Override
    protected SubjectResponseDto mapEntityToRes(SubjectEntity entity) {
        return modelMapper.map(entity, SubjectResponseDto.class);
    }

    @Override
    protected SubjectEntity mapCRToEntity(SubjectRequestDto subjectRequestDto) {
        return modelMapper.map(subjectRequestDto, SubjectEntity.class);
    }
}
