package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Course;
import com.example.Stepway.Domain.Enrollment;
import com.example.Stepway.Domain.Jobs;
import com.example.Stepway.Domain.User;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.JobsRepository;
import com.example.Stepway.Service.JobsService;
import com.example.Stepway.dto.EnrollmentDto;
import com.example.Stepway.dto.JobsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobsServiceImpl implements JobsService {

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<JobsDto> getALlJobs() {
        List<Jobs> jobs = jobsRepository.findAll();

        return jobs.stream().map(job -> modelMapper.map(job, JobsDto.class)).collect(Collectors.toList());
    }

    @Override
    public JobsDto getJobById(Long id) {
        Optional<Jobs> optionalJobs = jobsRepository.findById(id);
        Jobs jobs = optionalJobs.orElseThrow(() -> new ResourceNotFound("Job with the Id not Found : "+ id));
        return modelMapper.map(jobs,JobsDto.class);
    }

    @Override
    public JobsDto createJob(JobsDto jobsDto) {
        Jobs jobs = modelMapper.map(jobsDto,Jobs.class);
        Jobs savedJobs = jobsRepository.save(jobs);

        return modelMapper.map(savedJobs,JobsDto.class);
    }

    @Override
    public JobsDto updateJobById(Long id, JobsDto jobsDto) {
        Jobs jobs = jobsRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Job not Found with the id : "+id));


        jobs.setTitle(jobsDto.getTitle());
        jobs.setCompanyName(jobsDto.getCompanyName());
        jobs.setDescription(jobsDto.getDescription());
        jobs.setAddress(jobsDto.getAddress());
        jobs.setSalary(jobsDto.getSalary());
        jobs.setPostedDate(jobsDto.getPostedDate());


        Jobs updatedJob = jobsRepository.save(jobs);
        return modelMapper.map(updatedJob,JobsDto.class);
    }

    @Override
    public void deleteJobById(Long id) {
        if(!jobsRepository.existsById(id)){
            throw new ResourceNotFound("Job not found with the id : "+id);
        }
        jobsRepository.deleteById(id);
    }
}
