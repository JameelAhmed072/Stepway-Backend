package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Profile;
import com.example.Stepway.Domain.Resume;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.ResumeRepository;
import com.example.Stepway.Service.ResumeService;
import com.example.Stepway.dto.ResumeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ResumeDto> getALlResumes() {
        List<Resume> resumes = resumeRepository.findAll();

        return resumes.stream().map(resume -> modelMapper.map(resume, ResumeDto.class)).collect(Collectors.toList());
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        Optional<Resume> optionalResume = resumeRepository.findById(id);
        Resume resume = optionalResume.orElseThrow(() -> new ResourceNotFound("Resume with the Id not Found : "+ id));
        return modelMapper.map(resume,ResumeDto.class);
    }

    @Override
    public ResumeDto createResume(ResumeDto resumeDto) {
        Resume resume = modelMapper.map(resumeDto,Resume.class);
        Resume savedResume = resumeRepository.save(resume);

        return modelMapper.map(savedResume,ResumeDto.class);
    }

    @Override
    public ResumeDto updateResumeById(Long id, ResumeDto resumeDto) {
        Resume resume = resumeRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Resume not Found with the id : "+id));

        resume.setUrl(resumeDto.getUrl());

        Resume updatedResume = resumeRepository.save(resume);
        return modelMapper.map(updatedResume,ResumeDto.class);
    }

    @Override
    public void deleteResumeById(Long id) {
        if(!resumeRepository.existsById(id)){
            throw new ResourceNotFound("Resume not found with the id : "+id);
        }
        resumeRepository.deleteById(id);
    }
}
