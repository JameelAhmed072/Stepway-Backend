package com.example.Stepway.Service;

import com.example.Stepway.Domain.User;
import com.example.Stepway.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<UserDto> getAllUser(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    public UserDto updateUser(Long id, UserDto userDto);

    public void deleteUser(Long id);


}
