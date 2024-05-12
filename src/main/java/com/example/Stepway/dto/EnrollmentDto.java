package com.example.Stepway.dto;


import com.example.Stepway.Domain.Course;
import com.example.Stepway.Domain.User;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnrollmentDto {
    private Long id;

    private Long userId;

    private Long courseId;
}
