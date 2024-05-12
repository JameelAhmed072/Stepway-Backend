package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Permission;
import com.example.Stepway.Domain.Resume;
import com.example.Stepway.Domain.Role;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.RoleRepository;
import com.example.Stepway.Service.RoleService;
import com.example.Stepway.dto.ResumeDto;
import com.example.Stepway.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<RoleDto> getALlRoles() {
        List<Role> roles = roleRepository.findAll();

        return roles.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public RoleDto getRolesById(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        Role role = optionalRole.orElseThrow(() -> new ResourceNotFound("Role with the Id not Found : "+ id));
        return modelMapper.map(role,RoleDto.class);
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {

        Optional<Role> existingRole = roleRepository.findById(roleDto.getId());
        Set<Permission> truePermissions = new HashSet<>();

        if (existingRole.isPresent() && roleDto!= null) {
            for(Permission permission: roleDto.getPermissions()){
                if(permission.isStatus()){
                    truePermissions.add(permission);
                }
            }
            existingRole.get().setPermissions(truePermissions);
        }
//
//        Role roles = modelMapper.map(roleDto,Role.class);
//        Role savedRole = roleRepository.save(existingRole);

        return modelMapper.map(roleRepository.save(existingRole.get()),RoleDto.class);
    }

    @Override
    public RoleDto updateRoleById(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Role not Found with the id : "+id));

        role.setName(roleDto.getName());
        role.setStatus(Boolean.TRUE);

        Role updatedRole = roleRepository.save(role);
        return modelMapper.map(updatedRole,RoleDto.class);
    }

    @Override
    public void deleteRoleById(Long id) {
        if(!roleRepository.existsById(id)){
            throw new ResourceNotFound("Role not found with the id : "+id);
        }
        roleRepository.deleteById(id);
    }
}
