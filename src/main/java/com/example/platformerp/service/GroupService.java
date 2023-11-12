package com.example.platformerp.service;

import com.example.platformerp.dto.group.GroupRequestDto;
import com.example.platformerp.dto.group.GroupResponseDto;
import com.example.platformerp.dto.user.UserCreatedDto;
import com.example.platformerp.dto.user.UserResponseDto;
import com.example.platformerp.enums.Permissions;
import com.example.platformerp.exceptions.DataNotFoundException;
import com.example.platformerp.exceptions.responseException.RestException;
import com.example.platformerp.model.GroupEntity;
import com.example.platformerp.model.UserEntity;
import com.example.platformerp.model.UserRole;
import com.example.platformerp.repository.GroupRepository;
import com.example.platformerp.repository.UserRepository;
import com.example.platformerp.service.base.BaseService;
import com.example.platformerp.service.validator.GroupValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService extends BaseService<
        GroupEntity, UUID,GroupRepository, GroupRequestDto, GroupResponseDto, GroupValidator
        > {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public GroupService(GroupRepository repository, GroupValidator validator, ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(repository, validator, modelMapper);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected GroupResponseDto mapEntityToRes(GroupEntity entity) {
        GroupResponseDto groupResponseDto = modelMapper.map(entity, GroupResponseDto.class);
        Set<UserResponseDto> studentResponseDtos=new HashSet<>();
        for (UserEntity student : entity.getStudents()) {
            studentResponseDtos.add(
                    modelMapper.map(student, UserResponseDto.class)
            );
        }
        groupResponseDto.setStudents(studentResponseDtos);
        return groupResponseDto;
    }

    @Override
    protected GroupEntity mapCRToEntity(GroupRequestDto groupRequestDto) {
        return modelMapper.map(groupRequestDto, GroupEntity.class);
    }

    public GroupResponseDto addStudent(UUID studentId,UUID groupId) {
        GroupEntity group = repository.findById(groupId).orElseThrow(()-> new RestException(
                "group not found by id while adding student",
                HttpStatus.NOT_FOUND
        ));
        UserEntity userEntity = userRepository.findById(studentId).orElseThrow(() -> new RestException(
                "user not found by id while adding student",
                HttpStatus.NOT_FOUND
        ));
        group.getStudents().add(userEntity);
        repository.save(group);

        return mapEntityToRes(group);

    }

    public GroupResponseDto addStudents(List<UUID> studentIds,UUID groupId){
        GroupEntity group = repository.findById(groupId).orElseThrow(()-> new RestException(
                "group not found by id while adding students",
                HttpStatus.BAD_REQUEST
        ));
        List<UserEntity> newStudents=new ArrayList<>();
        for (UUID studentId : studentIds) {
            newStudents.add(
                    userRepository.findById(studentId).orElseThrow(()-> new RestException(
                            "user not found by id while adding students",
                            HttpStatus.BAD_REQUEST
                    ))
            );
        }

        group.getStudents().addAll(newStudents);

        return mapEntityToRes(group);
    }
}
