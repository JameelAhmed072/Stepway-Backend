package com.example.Stepway.dto;


import com.example.Stepway.Domain.Enrollment;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentDto {

    private Long id;

    @NotNull(message = "Amount should not be Null")
    private Double amount;

    private LocalDate transcationDate;

    private Long enrollmentId;
}
