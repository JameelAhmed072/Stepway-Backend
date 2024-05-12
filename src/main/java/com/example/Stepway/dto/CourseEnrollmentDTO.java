package com.example.Stepway.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@Data

public class CourseEnrollmentDTO {

    private Long courseId;
    private String courseName;
    private Long count;


    public CourseEnrollmentDTO(Long courseId, String courseName, Long count) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.count = count;
    }


}
