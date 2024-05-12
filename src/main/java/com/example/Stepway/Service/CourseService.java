package com.example.Stepway.Service;


import com.example.Stepway.dto.CourseDto;
import com.example.Stepway.dto.ProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<CourseDto> getALlCourses(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    public CourseDto getCourseById(Long id);

    public CourseDto createCourse(CourseDto courseDto);

    CourseDto updateCourseById(Long id,CourseDto courseDto);


    public void deleteCourseById(Long id);

}
