package com.example.Stepway.Service;

import com.example.Stepway.dto.InstitutionsDto;
import com.example.Stepway.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstitutionsService {

    public List<InstitutionsDto> getAllInstitutions(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    InstitutionsDto createInstitutions(InstitutionsDto institutionsDto);
}
