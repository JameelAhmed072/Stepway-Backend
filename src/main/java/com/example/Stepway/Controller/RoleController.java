package com.example.Stepway.Controller;


import com.example.Stepway.Service.impl.RoleServiceImpl;
import com.example.Stepway.dto.ResumeDto;
import com.example.Stepway.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class RoleController {

    @Autowired
    RoleServiceImpl roleServiceImpl;


    @PostMapping("/role")
    public ResponseEntity<RoleDto> addRole(@Valid @RequestBody RoleDto roleDto){

        RoleDto createRole = roleServiceImpl.createRole(roleDto);
        return ResponseEntity.ok(createRole);
    }
    @GetMapping("/role/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
        RoleDto roleDto = roleServiceImpl.getRolesById(id);

        return ResponseEntity.ok(roleDto);
    }
    @GetMapping("/allRoles")
    public ResponseEntity<List<RoleDto>> getAllRoles(){

        List<RoleDto> resumes = roleServiceImpl.getALlRoles();

        return ResponseEntity.ok(resumes);
    }
    @PutMapping("/role/{id}")
    public ResponseEntity<RoleDto> updateRole(@Valid @RequestBody RoleDto roleDto, @PathVariable Long id){

        RoleDto role = roleServiceImpl.updateRoleById(id,roleDto);

        return ResponseEntity.ok(role);
    }
    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<RoleDto> deleteRole(@PathVariable Long id){
        roleServiceImpl.deleteRoleById(id);

        return ResponseEntity.noContent().build();
    }
}
