package com.example.Stepway.dto;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDto {


    private Long id;
    @NotBlank(message = "User FirstName cannot be Blank")
    private String firstName;
    @NotBlank(message = "User LastName cannot be Blank")
    private String lastName;

    @NotNull(message = "User email should not be Null")
    private String email;
    @NotNull(message = "User password should not be Null")
    private String password;
    private String gender;

    private String phoneNumber;

    @NotNull(message = "User role should not be Null")
    private String Role;
}
