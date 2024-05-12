package com.example.Stepway.Controller;


import com.example.Stepway.Domain.Course;
import com.example.Stepway.Service.impl.CourseServiceImpl;
import com.example.Stepway.dto.CourseDto;
import com.example.Stepway.dto.CourseEnrollmentDTO;
import com.example.Stepway.dto.CustomUserDetails;
import com.example.Stepway.utils.AppConstants;
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
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCoursesForCurrentUser() {
        // Get the currently logged-in user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && ((Authentication) authentication).getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long currentUserId = userDetails.getUserId();
            // Use the user ID to fetch courses
            List<Course> userCourses = courseService.getCoursesForUser(currentUserId);
            return ResponseEntity.ok(userCourses);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/enrollcourses")
    public ResponseEntity<List<Course>> getAvailableCoursesForEnrollmentofCurrentUser() {
        // Get the currently logged-in user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && ((Authentication) authentication).getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long currentUserId = userDetails.getUserId();
            // Use the user ID to fetch courses
            List<Course> userCourses = courseService.getAvailableCoursesForEnrollment(currentUserId);
            return ResponseEntity.ok(userCourses);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/course")
    public ResponseEntity<CourseDto> addCourse(@Valid @RequestBody CourseDto courseDto){
        CourseDto createCourse = courseService.createCourse(courseDto);
        return ResponseEntity.ok(createCourse);
    }
    @GetMapping("/course/{id}")
    public ResponseEntity<CourseDto> getCourseById(@Valid @PathVariable Long id){
        CourseDto courseDto = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDto);
    }
    @GetMapping("/allCourses")
    public ResponseEntity<List<CourseDto>> getAllCourses(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR,required = false) String sortDir

    ){

        List<CourseDto> courses = courseService.getALlCourses(pageNumber,pageSize,sortBy,sortDir);

        return ResponseEntity.ok(courses);
    }
    @PutMapping("/course/{id}")
    public ResponseEntity<CourseDto> updateCourse(@Valid @RequestBody CourseDto courseDto, @PathVariable Long id){

        CourseDto course = courseService.updateCourseById(id,courseDto);

        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<CourseDto> deleteCourseById(@PathVariable Long id){
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/earning")
    public ResponseEntity<Long> totalEarning(){
        Long totEarning = courseService.totalEarning();

        return ResponseEntity.ok(totEarning);
    }
    @GetMapping("/totalCourses")
    public ResponseEntity<Long> totalCourses(){
        Long totCourses = courseService.totalCourses();

        return ResponseEntity.ok(totCourses);
    }


    @GetMapping("/courses/{name}")
    public ResponseEntity<List<Course>> searchByFirstLetter(@PathVariable("name") String name){

        List<Course> c = courseService.searchByFirstLetter(name);
        return ResponseEntity.ok(c);
    }




}
