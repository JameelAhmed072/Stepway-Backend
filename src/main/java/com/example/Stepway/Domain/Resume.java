package com.example.Stepway.Domain;


import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;  ///   ask about this from sir

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

}
