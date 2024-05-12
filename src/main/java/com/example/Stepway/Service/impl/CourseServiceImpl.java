package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Course;
import com.example.Stepway.Domain.Profile;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.CourseRepository;
import com.example.Stepway.Service.CourseService;
import com.example.Stepway.dto.CourseDto;
import com.example.Stepway.dto.CourseEnrollmentDTO;
import com.example.Stepway.dto.ProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CourseDto> getALlCourses(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize, sort);

        Page<Course> pageCourse = courseRepository.findAll(p);

        List<Course> allCourses = pageCourse.getContent();

        return allCourses.stream().map(course -> modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }

    public Long totalEarning(){

        Long totalEarning = courseRepository.totalEarning();

        return totalEarning;
    }

    public Long totalCourses(){
        long l = courseRepository.countCourses();
        return l;
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        Course course = optionalCourse.orElseThrow(() -> new ResourceNotFound("Course with the Id not Found : "+ id));
        return modelMapper.map(course,CourseDto.class);
    }

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto,Course.class);
        Course savedCourse = courseRepository.save(course);

        return modelMapper.map(savedCourse,CourseDto.class);
    }

    @Override
    public CourseDto updateCourseById(Long id, CourseDto courseDto) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Course not Found with the id : "+id));

        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course.setPrice(courseDto.getPrice());
        course.setType(courseDto.getType());
        course.setDiscount(courseDto.getDiscount());
        course.setStartDate(courseDto.getStartDate());
        course.setEndDate(courseDto.getEndDate());

        Course updatedCourse = courseRepository.save(course);
        return modelMapper.map(updatedCourse,CourseDto.class);
    }

    @Override
    public void deleteCourseById(Long id) {


        if(!courseRepository.existsById(id)){
            throw new ResourceNotFound("Course not found with the id : "+id);
        }
        courseRepository.deleteById(id);
    }


    public List<Course> searchByFirstLetter(String c) {
        // You can add validation or additional logic here if needed
        List<Course> cr = courseRepository.searchByFirstLetter(c);
        return cr;
    }

    public List<Course> getCoursesForUser(Long userId) {
        // Implement logic to fetch courses for a specific user by their user ID.
        // This can vary based on your data model and repository structure.
        // Here's a simplified example:

        List<Course> userCourses = courseRepository.getAllEnrolledCourses(userId);

        return userCourses;
    }
    public List<Course> getAvailableCoursesForEnrollment(Long userId) {
        // Implement logic to fetch courses for a specific user by their user ID.
        // This can vary based on your data model and repository structure.
        // Here's a simplified example:

        List<Course> userCourses = courseRepository.getAllAvailableEnrolledCourses(userId);

        return userCourses;
    }







}
