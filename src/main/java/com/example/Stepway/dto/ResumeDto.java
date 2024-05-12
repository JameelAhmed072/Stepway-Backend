package com.example.Stepway.dto;


import com.example.Stepway.Domain.User;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResumeDto {


    private Long id;

    private String url;  ///   ask about this from sir

    private Long userId;
}
