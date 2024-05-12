package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Profile;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.ProfileRepository;
import com.example.Stepway.Service.ProfileService;
import com.example.Stepway.dto.ProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProfileDto> getALlProfiles() {
        List<Profile> profiles = profileRepository.findAll();

        return profiles.stream().map(profile -> modelMapper.map(profile,ProfileDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProfileDto getProfileById(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        Profile profile = optionalProfile.orElseThrow(() -> new ResourceNotFound("Profile with the Id not Found : "+ id));
        return modelMapper.map(profile,ProfileDto.class);
    }

    @Override
    public ProfileDto createProfile(ProfileDto profileDto) {
        Profile profile = modelMapper.map(profileDto,Profile.class);
        Profile savedProfile = profileRepository.save(profile);

        return modelMapper.map(savedProfile,ProfileDto.class);
    }

    @Override
    public ProfileDto updateProfileById(Long id, ProfileDto profileDto) {
        Profile profile = profileRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Profile not Found with the id : "+id));

        profile.setBio(profileDto.getBio());


        Profile updatedProfile = profileRepository.save(profile);
        return modelMapper.map(updatedProfile,ProfileDto.class);
    }

    @Override
    public void deleteProfileById(Long id) {

        if(!profileRepository.existsById(id)){
            throw new ResourceNotFound("Profile not found with the id : "+id);
        }
        profileRepository.deleteById(id);

    }
}
