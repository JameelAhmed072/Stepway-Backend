package com.example.Stepway.Service;


import com.example.Stepway.dto.PermissionDto;
import com.example.Stepway.dto.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {

    List<PermissionDto> getALlPermissions();


    public PermissionDto getPermissionsById(Long id);

    public PermissionDto createPermission(PermissionDto permissionDto);

    PermissionDto updatePermissionById(Long id,PermissionDto permissionDto);

    public PermissionDto deletePermissionById(Long id);
}
