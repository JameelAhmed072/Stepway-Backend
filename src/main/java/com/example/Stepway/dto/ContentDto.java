package com.example.Stepway.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ContentDto {

    private Long id;

    private String title;

    private String url;

    private Long courseId;
}
