package com.example.Stepway.dto;


import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NoticeBoardDto {

    private Long id;

    private String title;

    private String details;
    private String postedBy;
    private String date;
}
