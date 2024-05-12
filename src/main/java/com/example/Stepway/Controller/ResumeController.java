package com.example.Stepway.Controller;


import com.example.Stepway.Service.impl.ResumeServiceImpl;
import com.example.Stepway.dto.ResumeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class ResumeController {

    @Autowired
    ResumeServiceImpl resumeServiceImpl;

    @PostMapping("/resume")
    public ResponseEntity<ResumeDto> addResume(@Valid @RequestBody ResumeDto resumeDto){

        ResumeDto createResume = resumeServiceImpl.createResume(resumeDto);
        return ResponseEntity.ok(createResume);
    }
    @GetMapping("/resume/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Long id){
        ResumeDto resumeDto = resumeServiceImpl.getResumeById(id);

        return ResponseEntity.ok(resumeDto);
    }
    @GetMapping("/allResumes")
    public ResponseEntity<List<ResumeDto>> getAllResume(){

        List<ResumeDto> resumes = resumeServiceImpl.getALlResumes();

        return ResponseEntity.ok(resumes);
    }
    @PutMapping("/resume/{id}")
    public ResponseEntity<ResumeDto> updateResume(@Valid @RequestBody ResumeDto resumeDto, @PathVariable Long id){

        ResumeDto resume = resumeServiceImpl.updateResumeById(id,resumeDto);

        return ResponseEntity.ok(resume);
    }
    @DeleteMapping("/deleteResume/{id}")
    public ResponseEntity<ResumeDto> deleteResume(@PathVariable Long id){
        resumeServiceImpl.deleteResumeById(id);

        return ResponseEntity.noContent().build();
    }




}
