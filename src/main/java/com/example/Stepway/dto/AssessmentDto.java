package com.example.Stepway.dto;


import com.example.Stepway.Domain.Course;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AssessmentDto {


    private Long id;

    private LocalDate date;

    @NotNull(message = "description should not be Null")
    private String description;

    private Long courseId;
}
