package com.pocu.catalog.converter;

import com.pocu.catalog.entity.SubjectEntity;
import com.pocu.catalog.web.dto.SubjectDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubjectConverter {

    public SubjectDto fromEntityToDto(SubjectEntity subjectEntity) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subjectEntity.getId());
        subjectDto.setOptional(subjectEntity.getOptional());
        subjectDto.setName(subjectEntity.getName());
        subjectDto.setCreditPoints(subjectEntity.getCreditPoints());

        return subjectDto;
    }

    public SubjectEntity fromDtoToEntity(SubjectDto subjectDto) {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(subjectDto.getId());
        subjectEntity.setName(subjectDto.getName());
        subjectEntity.setOptional(subjectDto.getOptional());
        subjectEntity.setCreditPoints(subjectDto.getCreditPoints());

        return subjectEntity;
    }

    public List<SubjectDto> fromEntitiesToDtos(List<SubjectEntity> subjectEntities) {
        return subjectEntities
                .stream()
                .map(entity -> fromEntityToDto(entity))
                .collect(Collectors.toList());
    }

    public List<SubjectEntity> fromDtosToEntities(List<SubjectDto> subjectDtos) {
        return subjectDtos
                .stream()
                .map(dto -> fromDtoToEntity(dto))
                .collect(Collectors.toList());
    }


}
