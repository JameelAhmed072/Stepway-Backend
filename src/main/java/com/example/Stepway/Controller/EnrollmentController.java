package com.example.Stepway.Controller;

import com.example.Stepway.Service.impl.EnrollmentServiceImpl;
import com.example.Stepway.dto.CourseEnrollmentDTO;
import com.example.Stepway.dto.CustomUserDetails;
import com.example.Stepway.dto.EnrollmentDto;
import com.example.Stepway.dto.ProfileDto;
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

public class EnrollmentController {

    @Autowired
    EnrollmentServiceImpl enrollmentServiceImpl;

    @PostMapping("/enrollment")
    public ResponseEntity<EnrollmentDto> addEnrollment(@Valid @RequestBody EnrollmentDto enrollmentDto){

        EnrollmentDto createEnrollment = enrollmentServiceImpl.createEnrollment(enrollmentDto);

        return ResponseEntity.ok(createEnrollment);
    }
    @PostMapping("/available-enrollment")
    public ResponseEntity<EnrollmentDto> addEnrollmentForCurrentUser(@RequestBody EnrollmentDto enrollmentDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && ((Authentication) authentication).getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long currentUserId = userDetails.getUserId();
            enrollmentDto.setUserId(currentUserId);
            EnrollmentDto createEnrollment = enrollmentServiceImpl.createEnrollment(enrollmentDto);
            return ResponseEntity.ok(createEnrollment);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/enrollment/{id}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@Valid @PathVariable Long id){

        EnrollmentDto enrollmentDto = enrollmentServiceImpl.getEnrollmentById(id);

        return ResponseEntity.ok(enrollmentDto);
    }
    @GetMapping("/allEnrollments")
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollment(){

        List<EnrollmentDto> enrollments = enrollmentServiceImpl.getALlEnrollments();

        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/enrollment/{id}")
    public ResponseEntity<EnrollmentDto> updateEnrollment(@Valid @RequestBody EnrollmentDto enrollmentDto, @PathVariable Long id){

        EnrollmentDto enrollment = enrollmentServiceImpl.updateEnrollmentById(id,enrollmentDto);

        return ResponseEntity.ok(enrollment);
    }

    @DeleteMapping("/deleteEnrollment/{id}")
    public ResponseEntity<EnrollmentDto> deleteEnrollmentById(@PathVariable Long id){
        enrollmentServiceImpl.deleteEnrollmentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countCurrentUserEnrolledCourses")
    public ResponseEntity<Long> countCurrentUserEnrolledCourses() {

        // Get the currently logged-in user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && ((Authentication) authentication).getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long currentUserId = userDetails.getUserId();

            Long totalCoursesOfCurrentUser = enrollmentServiceImpl.countCurrentUserEnrolledCourses(currentUserId);
            return ResponseEntity.ok(totalCoursesOfCurrentUser);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/countTotalEnrollments")
    public ResponseEntity<Long> countTotalEnrollments() {
        Long totalEnrollments = enrollmentServiceImpl.countTotalEnrollments();
        return ResponseEntity.ok(totalEnrollments);
    }
    @GetMapping("/getTopEnrolledCourses")  //  this api is not working
    public ResponseEntity<List<String>> getTopEnrolledCourses(){

        List<String> topEnrolledCourses = enrollmentServiceImpl.getTopEnrolledCourses();
        return ResponseEntity.ok(topEnrolledCourses);
    }


    @GetMapping("/enrollment/count")
    public List<CourseEnrollmentDTO> getCoursesByEnrollmentCount() {
        return enrollmentServiceImpl.findCoursesByEnrollmentCount();
    }




}
