package com.example.Stepway.Service;

import com.example.Stepway.dto.EnrollmentDto;
import com.example.Stepway.dto.ProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnrollmentService {

    List<EnrollmentDto> getALlEnrollments();

    public EnrollmentDto getEnrollmentById(Long id);

    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto);
    EnrollmentDto updateEnrollmentById(Long id,EnrollmentDto enrollmentDto);
    public void deleteEnrollmentById(Long id);
}
