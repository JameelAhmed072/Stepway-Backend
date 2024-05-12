package com.example.Stepway.dto;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobsDto {

    private Long id;

    private String title;
    private String companyName;
    private String description;
    private String address;
    private Long salary;
    private LocalDate postedDate;
}
