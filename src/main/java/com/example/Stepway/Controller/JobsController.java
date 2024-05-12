package com.example.Stepway.Controller;


import com.example.Stepway.Service.impl.JobsServiceImpl;
import com.example.Stepway.dto.EnrollmentDto;
import com.example.Stepway.dto.JobsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class JobsController {

    @Autowired
    JobsServiceImpl jobsServiceImpl;

    @PostMapping("/job")
    public ResponseEntity<JobsDto> addJob(@Valid @RequestBody JobsDto jobsDto){

        JobsDto createJob = jobsServiceImpl.createJob(jobsDto);

        return ResponseEntity.ok(createJob);
    }
    @GetMapping("/job/{id}")
    public ResponseEntity<JobsDto> getJobById(@Valid @PathVariable Long id){

        JobsDto jobsDto = jobsServiceImpl.getJobById(id);

        return ResponseEntity.ok(jobsDto);
    }
    @GetMapping("/allJobs")
    public ResponseEntity<List<JobsDto>> getAllJobs(){

        List<JobsDto> jobs = jobsServiceImpl.getALlJobs();

        return ResponseEntity.ok(jobs);
    }
    @PutMapping("/job/{id}")
    public ResponseEntity<JobsDto> updateJob(@Valid @RequestBody JobsDto jobsDto, @PathVariable Long id){

        JobsDto job = jobsServiceImpl.updateJobById(id,jobsDto);

        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/deleteJob/{id}")
    public ResponseEntity<JobsDto> deleteJobById(@PathVariable Long id){
        jobsServiceImpl.deleteJobById(id);
        return ResponseEntity.noContent().build();
    }
}
