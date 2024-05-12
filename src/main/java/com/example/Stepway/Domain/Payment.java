package com.example.Stepway.Domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDate transcationDate;


    @OneToOne
    @JoinColumn(name = "enrollmentId", referencedColumnName = "id")
    private Enrollment enrollmentId;
}
