package com.example.Stepway.Controller;


import com.example.Stepway.Domain.Permission;
import com.example.Stepway.Service.impl.PermissionServiceImpl;
import com.example.Stepway.dto.PermissionDto;
import com.example.Stepway.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class PermissionController {

    @Autowired
    PermissionServiceImpl permissionServiceImpl;

    @PostMapping("/permission")
    public ResponseEntity<PermissionDto> addPermission(@Valid @RequestBody PermissionDto permissionDto){

        PermissionDto createPermission = permissionServiceImpl.createPermission(permissionDto);
        return ResponseEntity.ok(createPermission);
    }

    @GetMapping("/permission")
    public ResponseEntity<List<Permission>> getActivePermissions(){
        return ResponseEntity.ok(permissionServiceImpl.getActivePermissions());
    }
    @GetMapping("/permission/{id}")
    public ResponseEntity<PermissionDto> getPermissionById(@PathVariable Long id){
        PermissionDto permissionDto = permissionServiceImpl.getPermissionsById(id);

        return ResponseEntity.ok(permissionDto);
    }
    @GetMapping("/allPermission")
    public ResponseEntity<List<PermissionDto>> getAllPermissions(){

        List<PermissionDto> permissions = permissionServiceImpl.getALlPermissions();

        return ResponseEntity.ok(permissions);
    }
    @PutMapping("/permission/{id}")
    public ResponseEntity<PermissionDto> updatePermission(@Valid @RequestBody PermissionDto permissionDto, @PathVariable Long id){

        PermissionDto permission = permissionServiceImpl.updatePermissionById(id,permissionDto);

        return ResponseEntity.ok(permission);
    }
    @DeleteMapping("/deletePermission/{id}")
    public ResponseEntity<PermissionDto> deletePermission(@PathVariable Long id){
        permissionServiceImpl.deletePermissionById(id);

        return ResponseEntity.noContent().build();
    }
}
