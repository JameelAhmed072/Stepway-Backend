package com.example.Stepway.Service;


import com.example.Stepway.dto.ResumeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {

    List<ResumeDto> getALlResumes();

    public ResumeDto getResumeById(Long id);

    public ResumeDto createResume(ResumeDto resumeDto);

    ResumeDto updateResumeById(Long id,ResumeDto resumeDto);

    public void deleteResumeById(Long id);
}
