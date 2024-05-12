package com.example.Stepway.dto;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseDto {

    private Long id;


    @NotBlank(message = "courseName should not be blank")
    private String courseName;

    private String description;

    @NotNull(message = "Course price should not be Null")
    private Long price;

    private Long discount;

    @NotNull(message = "Course start date should not be Null")
    private LocalDate startDate;
    @NotNull(message = "Course end date should not be Null")
    private LocalDate endDate;

    @NotBlank(message = "Course type should not be Blank")
    private String type;  //  this is either a course or a workshop
}
