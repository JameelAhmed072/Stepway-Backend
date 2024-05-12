package com.example.Stepway.Service;


import com.example.Stepway.Domain.Profile;
import com.example.Stepway.dto.ProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {

    List<ProfileDto> getALlProfiles();

    public ProfileDto getProfileById(Long id);

    public ProfileDto createProfile(ProfileDto profileDto);

    ProfileDto updateProfileById(Long id,ProfileDto profileDto);

    public void deleteProfileById(Long id);


}
