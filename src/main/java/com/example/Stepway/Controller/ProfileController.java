package com.example.Stepway.Controller;

import com.example.Stepway.Service.impl.ProfileServiceImpl;
import com.example.Stepway.dto.ProfileDto;
import com.example.Stepway.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class ProfileController {

    @Autowired
    ProfileServiceImpl profileServiceimpl;

    @PostMapping("/profile")
    public ResponseEntity<ProfileDto> addProfile(@Valid @RequestBody ProfileDto profileDto){

        ProfileDto createProfile = profileServiceimpl.createProfile(profileDto);

        return ResponseEntity.ok(createProfile);
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@Valid @PathVariable Long id){

        ProfileDto profileDto = profileServiceimpl.getProfileById(id);

        return ResponseEntity.ok(profileDto);
    }
    @GetMapping("/allProfiles")
    public ResponseEntity<List<ProfileDto>> getAllProfiles(){

        List<ProfileDto> profiles = profileServiceimpl.getALlProfiles();

        return ResponseEntity.ok(profiles);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@Valid @RequestBody ProfileDto profileDto, @PathVariable Long id){

        ProfileDto profile = profileServiceimpl.updateProfileById(id,profileDto);

        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/deleteProfile/{id}")
    public ResponseEntity<ProfileDto> deleteProfileById(@PathVariable Long id){
        profileServiceimpl.deleteProfileById(id);
        return ResponseEntity.noContent().build();
    }

}
