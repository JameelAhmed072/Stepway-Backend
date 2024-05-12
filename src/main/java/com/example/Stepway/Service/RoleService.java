package com.example.Stepway.Service;


import com.example.Stepway.dto.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<RoleDto> getALlRoles();


    public RoleDto getRolesById(Long id);

    public RoleDto createRole(RoleDto roleDto);

    RoleDto updateRoleById(Long id,RoleDto roleDto);

    public void deleteRoleById(Long id);
}
