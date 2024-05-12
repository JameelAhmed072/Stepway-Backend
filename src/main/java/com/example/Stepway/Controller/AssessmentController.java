package com.example.Stepway.Controller;

import com.example.Stepway.Service.impl.AssessmentServiceImpl;
import com.example.Stepway.dto.AssessmentDto;
import com.example.Stepway.dto.CertificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class AssessmentController {

    @Autowired
    AssessmentServiceImpl assessmentServiceImpl;

    @PostMapping("/assessment")
    public ResponseEntity<AssessmentDto> addAssessment(@Valid @RequestBody AssessmentDto assessmentDto){

        AssessmentDto createAssessment = assessmentServiceImpl.createAssessment(assessmentDto);

        return ResponseEntity.ok(createAssessment);
    }
    @GetMapping("/assessment/{id}")
    public ResponseEntity<AssessmentDto> getAssessmentById(@Valid @PathVariable Long id){

        AssessmentDto assessmentDto = assessmentServiceImpl.getAssessmentById(id);

        return ResponseEntity.ok(assessmentDto);
    }

    @GetMapping("/allAssessments")
    public ResponseEntity<List<AssessmentDto>> getAllAssessments(){

        List<AssessmentDto> assessment = assessmentServiceImpl.getALlAssessments();

        return ResponseEntity.ok(assessment);
    }

    @GetMapping("/allAssessments/{courseId}")
    public ResponseEntity<List<AssessmentDto>> getAllAssessmentsByCourseId(@PathVariable Long courseId){

        List<AssessmentDto> assessment = assessmentServiceImpl.getAllAssessmentsByCourseId(courseId);

        return ResponseEntity.ok(assessment);
    }

//    @PutMapping("/assessment/{id}")
//    public ResponseEntity<AssessmentDto> updateAssessment(@Valid @RequestBody AssessmentDto assessmentDto, @PathVariable Long id){
//
//        AssessmentDto assessment = assessmentServiceImpl.updateAssessmentById(id,assessmentDto);
//
//        return ResponseEntity.ok(assessment);
//    }

    @DeleteMapping("/deleteAssessment/{id}")
    public ResponseEntity<AssessmentDto> deleteAssessmentById(@PathVariable Long id){
        assessmentServiceImpl.deleteProfileById(id);
        return ResponseEntity.noContent().build();
    }

}
