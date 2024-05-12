package com.example.Stepway.Controller;


import com.example.Stepway.Service.impl.MyUserDetailService;
import com.example.Stepway.payload.JwtAuthenticationResponse;
import com.example.Stepway.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(@RequestBody LoginCredentials loginCredentials) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginCredentials.getEmail(),loginCredentials.getPassword())
            );
        }
        catch(BadCredentialsException e){
            throw new Exception("Incorrect Username or Password ! ",e);
        }
        //   If email and password are correct and the below lines will run
        UserDetails userDetails = myUserDetailService.loadUserByUsername(loginCredentials.getEmail());
        String jwtToken = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
    }
}

