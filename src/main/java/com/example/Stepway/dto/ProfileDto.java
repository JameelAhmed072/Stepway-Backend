package com.example.Stepway.dto;

import com.example.Stepway.Domain.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProfileDto {


    private Long id;

    private String bio;


    @NotNull(message = "User ID cannot be null")
    private Long userId;
}
