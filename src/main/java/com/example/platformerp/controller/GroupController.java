package com.example.platformerp.controller;

import com.example.platformerp.dto.group.GroupRequestDto;
import com.example.platformerp.dto.group.GroupResponseDto;

import com.example.platformerp.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER') and hasAuthority('GROUP_CREATE')")
    public GroupResponseDto save(@RequestBody GroupRequestDto groupRequestDto){
        return groupService.create(groupRequestDto);
    }
    @GetMapping("find")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN','MANAGER') and hasAuthority('GROUP_GET')")
    public GroupResponseDto getById(@RequestParam UUID id){
        return groupService.findById(id);
    }
    @GetMapping("get-all")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN','MANAGER','TEACHER') and hasAuthority('GROUP_GET')")
    public List<GroupResponseDto> getAll(@RequestParam(defaultValue = "1") int size,@RequestParam(defaultValue = "1") int page){
        return groupService.getAll(size, page);
    }

    @PostMapping("/add-student")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('GROUP_UPDATE')")
    public GroupResponseDto addStudent(@RequestParam UUID studentId,@RequestParam UUID groupId){
        return groupService.addStudent(studentId,groupId);
    }
    @PostMapping("/add-students")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('GROUP_UPDATE')")
    public GroupResponseDto addStudents(@RequestBody List<UUID> studentIds,@RequestParam UUID groupId){
        return groupService.addStudents(studentIds,groupId);
    }
}
