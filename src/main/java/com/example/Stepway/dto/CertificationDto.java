package com.example.Stepway.dto;

import com.example.Stepway.Domain.User;
import lombok.*;

import javax.persistence.*;
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

public class CertificationDto {

    private Long id;
    @NotBlank(message = "Certification Name should not be Blank")
    private String name;

    @NotNull(message = "Certfification date must be mentioned")
    private LocalDate dateEarned;
    @NotNull(message = "User ID cannot be null")

    private Long courseId;
    private Long userId;
}
