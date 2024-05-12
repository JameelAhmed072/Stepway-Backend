package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Assessment;
import com.example.Stepway.Domain.Certification;
import com.example.Stepway.Domain.Course;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.AssessmentRepository;
import com.example.Stepway.Repository.CourseRepository;
import com.example.Stepway.Service.AssessmentService;
import com.example.Stepway.dto.AssessmentDto;
import com.example.Stepway.dto.CertificationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    AssessmentRepository assessmentRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<AssessmentDto> getALlAssessments() {
        List<Assessment> assessments = assessmentRepository.findAll();

        return assessments.stream().map(assessment -> modelMapper.map(assessment, AssessmentDto.class)).collect(Collectors.toList());
    }

    public List<AssessmentDto> getAllAssessmentsByCourseId(Long courseId) {
        List<Assessment> assessments = assessmentRepository.findByCourseId(courseId);

        return assessments.stream().map(assessment -> modelMapper.map(assessment, AssessmentDto.class)).collect(Collectors.toList());
    }

    @Override
    public AssessmentDto getAssessmentById(Long id) {
        Optional<Assessment> optionalAssessment = assessmentRepository.findById(id);
        Assessment assessment = optionalAssessment.orElseThrow(() -> new ResourceNotFound("Assessment with the Id not Found : "+ id));
        return modelMapper.map(assessment,AssessmentDto.class);
    }

    @Override
    public AssessmentDto createAssessment(AssessmentDto assessmentDto) {
        Assessment assessment = modelMapper.map(assessmentDto,Assessment.class);
        Assessment savedAssessment = assessmentRepository.save(assessment);

        return modelMapper.map(savedAssessment,AssessmentDto.class);
    }

//    @Override
//    public AssessmentDto updateAssessmentById(Long id, AssessmentDto assessmentDto) {
//        Assessment assessment = assessmentRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Assessment not Found with the id : "+id));
//
//        Course course = courseRepository.findById(assessmentDto.getCourseId()).orElseThrow(()->new ResourceNotFound("Course with this id is not found : "+ assessmentDto.getCourseId()));
//
//
//
//        assessment.setDate(assessmentDto.getDate());
//        assessment.setDescription(assessmentDto.getDescription());
//        assessment.setCourseId(course);
//
//
//        Assessment updatedAssessment = assessmentRepository.save(assessment);
//        return modelMapper.map(updatedAssessment,AssessmentDto.class);
//    }

    @Override
    public void deleteProfileById(Long id) {
        if(!assessmentRepository.existsById(id)){
            throw new ResourceNotFound("Assessment not found with the id : "+id);
        }
        assessmentRepository.deleteById(id);
    }
}
