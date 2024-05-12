package com.example.Stepway.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;
}
