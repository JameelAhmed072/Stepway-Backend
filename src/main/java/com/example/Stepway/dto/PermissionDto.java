package com.example.Stepway.dto;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder


public class PermissionDto {

    private Long id;

    private String name;

    private boolean status;
}
