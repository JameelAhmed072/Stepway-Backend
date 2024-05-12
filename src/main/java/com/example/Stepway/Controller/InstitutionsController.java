package com.example.Stepway.Controller;


import com.example.Stepway.Service.impl.InstitutionsServiceImpl;
import com.example.Stepway.dto.InstitutionsDto;
import com.example.Stepway.dto.UserDto;
import com.example.Stepway.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InstitutionsController {

    @Autowired
    private InstitutionsServiceImpl institutionsService;
    @PostMapping("/institute")
    public ResponseEntity<InstitutionsDto> addInstitute(@RequestBody InstitutionsDto institutionsDto){
        InstitutionsDto createUser = institutionsService.createInstitutions(institutionsDto);
        return ResponseEntity.ok(createUser);

    }
    @GetMapping("/allInstitute")
    public ResponseEntity<List<InstitutionsDto>> getAllInstitute(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR,required = false) String sortDir

    ){
        List<InstitutionsDto> institutions = institutionsService.getAllInstitutions(pageNumber,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(institutions);
    }
}
