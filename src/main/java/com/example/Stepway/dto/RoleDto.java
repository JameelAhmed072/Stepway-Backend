package com.example.Stepway.dto;


import com.example.Stepway.Domain.Permission;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleDto {

    private Long id;

    @NotBlank(message = "Name should not be Blank")
    private String name;

    private Set<Permission> permissions = new HashSet<>();
}
