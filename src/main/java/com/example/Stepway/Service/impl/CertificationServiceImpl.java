package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Certification;
import com.example.Stepway.Domain.Course;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.CertificationRepository;
import com.example.Stepway.Repository.CourseRepository;
import com.example.Stepway.Service.CertificationService;
import com.example.Stepway.dto.CertificationDto;
import com.example.Stepway.dto.CourseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    CertificationRepository certificationRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<CertificationDto> getALlCertifications() {
        List<Certification> certifications = certificationRepository.findAll();

        return certifications.stream().map(certification -> modelMapper.map(certification, CertificationDto.class)).collect(Collectors.toList());
    }

    @Override
    public CertificationDto getCertificationById(Long id) {
        Optional<Certification> optionalCertification = certificationRepository.findById(id);
        Certification certification = optionalCertification.orElseThrow(() -> new ResourceNotFound("Certification with the Id not Found : "+ id));
        return modelMapper.map(certification,CertificationDto.class);
    }
    public List<CertificationDto> getCertificationByUserId(Long id) {
        List<Certification> certificationsByUserId = certificationRepository.getCertificationsByUserId(id);
        return certificationsByUserId.stream().map(certification -> modelMapper.map(certification,CertificationDto.class)).collect(Collectors.toList());
    }

    @Override
    public CertificationDto createCertification(CertificationDto certificationDto) {
        Certification certification = modelMapper.map(certificationDto,Certification.class);
        Certification savedCertification = certificationRepository.save(certification);


        return modelMapper.map(savedCertification,CertificationDto.class);
    }

    @Override
    public CertificationDto updateCertificationById(Long id, CertificationDto certificationDto) {
        Certification certification = certificationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Certification not Found with the id : "+id));

        certification.setName(certificationDto.getName());
        certification.setDateEarned(certificationDto.getDateEarned());

        Certification updatedCertification = certificationRepository.save(certification);
        return modelMapper.map(updatedCertification,CertificationDto.class);
    }

    @Override
    public void deleteCertificationById(Long id) {

        if(!certificationRepository.existsById(id)){
            throw new ResourceNotFound("Certificate not found with the id : "+id);
        }
        certificationRepository.deleteById(id);
    }

    public Long countCertificationById(Long id) {
        Long totalCertifications = certificationRepository.countUsersWithRoleStudent(id);
        return totalCertifications;
    }

    public Long countTotalCertifications() {
        Long totalCertifications = certificationRepository.countTotalCertifications();
        return totalCertifications;
    }


}
