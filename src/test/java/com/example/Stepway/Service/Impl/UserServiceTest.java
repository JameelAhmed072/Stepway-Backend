package com.example.Stepway.Service.Impl;


import com.example.Stepway.Domain.User;
import com.example.Stepway.Repository.UserRepository;
import com.example.Stepway.Service.impl.UserServiceImpl;
import com.example.Stepway.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @Autowired
    User user;

    @Autowired
    UserDto userDto;


    @BeforeEach
    public void beforeEach(){
        user.setId(1L);
        user.setFirstName("Jameel");
        user.setLastName("Ahmed");
        user.setPhoneNumber("0123123");
        user.setEmail("jameel@gmail.com");
        user.setPassword("jameel123");
        user.setRole(new HashSet<>());

        userDto.setId(1L);
        userDto.setFirstName("Jameel");
        userDto.setLastName("Ahmed");
        userDto.setPhoneNumber("0123123");
        userDto.setEmail("jameel@gmail.com");
        userDto.setPassword("jameel123");
        userDto.setRole("Student");
    }

    @Test
    public void getUserById_UserFound_ShouldReturnUserDto(){

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(modelMapper.map(user,UserDto.class));

        UserDto result = userService.getUserById(user.getId());

        assertNotNull(result);
        assertEquals(userDto,result);
    }

}
