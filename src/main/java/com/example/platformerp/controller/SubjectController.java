package com.example.platformerp.controller;


import com.example.platformerp.dto.subject.SubjectRequestDto;
import com.example.platformerp.dto.subject.SubjectResponseDto;
import com.example.platformerp.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService service;

    @GetMapping("/get{id}")
    public SubjectResponseDto getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/get-all")
    public List<SubjectResponseDto> getAll(@RequestParam(defaultValue = "1") int size, @RequestParam(defaultValue = "1") int page) {
        return service.getAll(size, page);
    }

    @PostMapping("/create")
    public SubjectResponseDto create(@RequestBody SubjectRequestDto subjectRequestDto) {
        return service.create(subjectRequestDto);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam UUID id) {
        service.deleteById(id);
    }
//    @PutMapping
//    public SubjectResponseDto update(@RequestBody UUID id,@RequestBody SubjectRequestDto subjectRequestDto){
//        return service.
//    }
}
