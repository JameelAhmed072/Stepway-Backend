package com.example.Stepway.Service;

import com.example.Stepway.dto.EnrollmentDto;
import com.example.Stepway.dto.JobsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobsService {

    List<JobsDto> getALlJobs();

    public JobsDto getJobById(Long id);

    public JobsDto createJob(JobsDto jobsDto);
    JobsDto updateJobById(Long id,JobsDto jobsDto);
    public void deleteJobById(Long id);
}
