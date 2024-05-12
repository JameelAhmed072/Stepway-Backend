package com.example.Stepway.payload;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    public JwtAuthenticationResponse(String accessToken){
        this.accessToken=accessToken;
    }

}
