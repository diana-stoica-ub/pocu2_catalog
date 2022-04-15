package com.pocu.catalog.web;

import com.pocu.catalog.converter.SubjectConverter;
import com.pocu.catalog.entity.SubjectEntity;
import com.pocu.catalog.service.SubjectService;
import com.pocu.catalog.web.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectConverter subjectConverter;

    @Autowired
    public SubjectController(SubjectService subjectService, SubjectConverter subjectConverter) {
        this.subjectService = subjectService;
        this.subjectConverter = subjectConverter;
    }

    @GetMapping(value = "")
    public List<SubjectDto> getSubjects() {
        return subjectConverter.fromEntitiesToDtos(subjectService.getSubjects());
    }

    @GetMapping(value = "/{id}")
    public SubjectDto getSubject(@PathVariable Long id) {
        return subjectConverter.fromEntityToDto(subjectService.getSubject(id));
    }

    @PostMapping(value = "")
    public SubjectDto addSubject(@RequestBody SubjectDto subjectDto) {
        SubjectEntity subjectEntity = subjectService.saveSubject(subjectConverter.fromDtoToEntity(subjectDto));

        return subjectConverter.fromEntityToDto(subjectEntity);
    }

    @PutMapping(value = "/{id}")
    public SubjectDto updateSubject(@PathVariable Long id, @RequestBody SubjectDto subjectDto) {
        SubjectEntity updatedEntity = subjectConverter.fromDtoToEntity(subjectDto);
        return subjectConverter.fromEntityToDto(subjectService.updateEntity(id, updatedEntity));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }

}
