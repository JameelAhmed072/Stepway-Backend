package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Course;
import com.example.Stepway.Domain.Enrollment;
import com.example.Stepway.Domain.Profile;
import com.example.Stepway.Domain.User;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.CourseRepository;
import com.example.Stepway.Repository.EnrollmentRespository;
import com.example.Stepway.Repository.UserRepository;
import com.example.Stepway.Service.EnrollmentService;
import com.example.Stepway.dto.CourseEnrollmentDTO;
import com.example.Stepway.dto.EnrollmentDto;
import com.example.Stepway.dto.ProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    EnrollmentRespository enrollmentRespository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<EnrollmentDto> getALlEnrollments() {
        List<Enrollment> enrollments = enrollmentRespository.findAll();

        return enrollments.stream().map(enrollment -> modelMapper.map(enrollment, EnrollmentDto.class)).collect(Collectors.toList());
    }

    @Override
    public EnrollmentDto getEnrollmentById(Long id) {
        Optional<Enrollment> optionalEnrollment = enrollmentRespository.findById(id);
        Enrollment enrollment = optionalEnrollment.orElseThrow(() -> new ResourceNotFound("Enrollment with the Id not Found : "+ id));
        return modelMapper.map(enrollment,EnrollmentDto.class);
    }

    @Override
    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = modelMapper.map(enrollmentDto,Enrollment.class);
        Enrollment savedEnrollment = enrollmentRespository.save(enrollment);

        return modelMapper.map(savedEnrollment,EnrollmentDto.class);
    }

    @Override
    public EnrollmentDto updateEnrollmentById(Long id, EnrollmentDto enrollmentDto) {
        Enrollment enrollment = enrollmentRespository.findById(id).orElseThrow(()-> new ResourceNotFound("Enrollment not Found with the id : "+id));

        User user = userRepository.findById(enrollmentDto.getUserId())
                    .orElseThrow(()->new ResourceNotFound("User Not found with this id: +"+  enrollmentDto.getUserId()));



        Course course = courseRepository.findById(enrollmentDto.getCourseId())
                .orElseThrow(()->new ResourceNotFound("Course Not found with this id: +"+  enrollmentDto.getCourseId()));

        enrollment.setUserId(user);
        enrollment.setCourseId(course);


        Enrollment updatedEnrollment = enrollmentRespository.save(enrollment);
        return modelMapper.map(updatedEnrollment,EnrollmentDto.class);
    }

    @Override
    public void deleteEnrollmentById(Long id) {
        if(!enrollmentRespository.existsById(id)){
            throw new ResourceNotFound("Enrollment not found with the id : "+id);
        }
        enrollmentRespository.deleteById(id);
    }

    public Long countCurrentUserEnrolledCourses(Long id) {
        Long totalEnrolledCourseByUserId = enrollmentRespository.countCurrentUserEnrolledCourses(id);
        return totalEnrolledCourseByUserId;
    }

    public Long countTotalEnrollments() {
        Long totalEnrollments = enrollmentRespository.countTotalEnrollments();
        return totalEnrollments;
    }
    public List<String> getTopEnrolledCourses() {

        List<Object[]> top5CourseNamesByEnrollmentCount = enrollmentRespository.findTop5CourseNamesByEnrollmentCount();

        // Extracting the course names from the result
        return top5CourseNamesByEnrollmentCount.stream()
                .map(data -> (String) data[0])
                .collect(Collectors.toList());
    }


    public List<CourseEnrollmentDTO> findCoursesByEnrollmentCount() {
        // Retrieve courses by enrollment count
        return enrollmentRespository.findCoursesByEnrollmentCount();
    }


}
