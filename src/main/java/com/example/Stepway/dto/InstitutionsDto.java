package com.example.Stepway.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class InstitutionsDto {

    private Long id;
    private String name;
    private String location;
    private String type;  // public or private
}
