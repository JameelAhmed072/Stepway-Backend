package com.example.Stepway.Service;

import com.example.Stepway.dto.AssessmentDto;
import com.example.Stepway.dto.CertificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssessmentService {

    List<AssessmentDto> getALlAssessments();


    public AssessmentDto getAssessmentById(Long id);

    public AssessmentDto createAssessment(AssessmentDto assessmentDto);

//    AssessmentDto updateAssessmentById(Long id,AssessmentDto assessmentDto);

    public void deleteProfileById(Long id);
}
