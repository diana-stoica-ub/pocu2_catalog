package com.pocu.catalog.service;

import com.pocu.catalog.entity.SubjectEntity;
import com.pocu.catalog.exception.SubjectNotFoundException;
import com.pocu.catalog.repository.SubjectRepository;
import com.pocu.catalog.web.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectEntity getSubject(Long id) {
        Optional<SubjectEntity> subjectEntityOptional = subjectRepository.findById(id);
        if (subjectEntityOptional.isPresent()) {
            return subjectEntityOptional.get();
        } else {
            throw new SubjectNotFoundException();
        }
    }

    public List<SubjectEntity> getSubjects() {
        return subjectRepository.findAll();
    }

    public SubjectEntity saveSubject(SubjectEntity subjectEntity) {
        return subjectRepository.save(subjectEntity);
    }

    public SubjectEntity updateEntity(Long id, SubjectEntity updatedEntity) {
        Optional<SubjectEntity> alreadyPersistedSubject = subjectRepository.findById(id);
        if (alreadyPersistedSubject.isPresent()) {
            updatedEntity.setId(id);
            return subjectRepository.save(updatedEntity);
        } else {
            throw new SubjectNotFoundException();
        }
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
