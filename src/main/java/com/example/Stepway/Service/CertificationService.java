package com.example.Stepway.Service;


import com.example.Stepway.dto.CertificationDto;
import com.example.Stepway.dto.ProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificationService {
    List<CertificationDto> getALlCertifications();


    public CertificationDto getCertificationById(Long id);

    public CertificationDto createCertification(CertificationDto certificationDto);

    CertificationDto updateCertificationById(Long id,CertificationDto certificationDto);

    public void deleteCertificationById(Long id);
}
