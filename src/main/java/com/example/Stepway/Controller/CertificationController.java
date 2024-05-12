package com.example.Stepway.Controller;

import com.example.Stepway.Service.impl.CertificationServiceImpl;
import com.example.Stepway.dto.CertificationDto;
import com.example.Stepway.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class CertificationController {

    @Autowired
    CertificationServiceImpl certificationServiceImpl;

    @PostMapping("/certification")
    public ResponseEntity<CertificationDto> addCertification(@Valid @RequestBody CertificationDto certificationDto){

        CertificationDto createCertification = certificationServiceImpl.createCertification(certificationDto);

        return ResponseEntity.ok(createCertification);
    }
    @GetMapping("/certification/{id}")
    public ResponseEntity<CertificationDto> getCertificationById(@Valid @PathVariable Long id){

        CertificationDto certificationDto = certificationServiceImpl.getCertificationById(id);

        return ResponseEntity.ok(certificationDto);
    }
    @GetMapping("/studentCertification")
    public ResponseEntity<List<CertificationDto>> getCertificationByUserId(){

        // Get the currently logged-in user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && ((Authentication) authentication).getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long currentUserId = userDetails.getUserId();

            List<CertificationDto> certificationByUserId = certificationServiceImpl.getCertificationByUserId(currentUserId);

            return ResponseEntity.ok(certificationByUserId);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/allCertifications")
    public ResponseEntity<List<CertificationDto>> getAllCertifications(){

        List<CertificationDto> certifications = certificationServiceImpl.getALlCertifications();

        return ResponseEntity.ok(certifications);
    }

    @PutMapping("/certification/{id}")
    public ResponseEntity<CertificationDto> updateCertification(@Valid @RequestBody CertificationDto certificationDto, @PathVariable Long id){

        CertificationDto certification = certificationServiceImpl.updateCertificationById(id,certificationDto);

        return ResponseEntity.ok(certification);
    }

    @DeleteMapping("/deleteCertification/{id}")
    public ResponseEntity<CertificationDto> deleteCertificationById(@PathVariable Long id){
        certificationServiceImpl.deleteCertificationById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/countCurrentUserCertificates")
    public ResponseEntity<Long> countCurrentStudentCertifications() {

        // Get the currently logged-in user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && ((Authentication) authentication).getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long currentUserId = userDetails.getUserId();

            Long totalCertificatesOfCurrentUser = certificationServiceImpl.countCertificationById(currentUserId);
            return ResponseEntity.ok(totalCertificatesOfCurrentUser);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/countTotalCertifications")
    public ResponseEntity<Long> countTotalCertifications() {
        Long totalCertificates = certificationServiceImpl.countTotalCertifications();
        return ResponseEntity.ok(totalCertificates);
    }
}
