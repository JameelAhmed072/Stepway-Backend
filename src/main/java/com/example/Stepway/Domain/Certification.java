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
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateEarned;

    @OneToOne
    private Course courseId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;
}
