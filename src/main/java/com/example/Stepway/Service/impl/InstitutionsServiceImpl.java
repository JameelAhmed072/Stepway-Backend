package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Institutions;
import com.example.Stepway.Domain.Role;
import com.example.Stepway.Domain.User;
import com.example.Stepway.Repository.InstitutionsRepo;
import com.example.Stepway.Service.InstitutionsService;
import com.example.Stepway.dto.InstitutionsDto;
import com.example.Stepway.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InstitutionsServiceImpl implements InstitutionsService {


    @Autowired
    private InstitutionsRepo institutionsRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<InstitutionsDto> getAllInstitutions(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {


        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize, sort);

        Page<Institutions> pageUser = institutionsRepo.findAll(p);
        List<Institutions> allInstitutions = pageUser.getContent();
        return allInstitutions.stream().map(user -> modelMapper.map(user,InstitutionsDto.class)).collect(Collectors.toList());

    }

    @Override
    public InstitutionsDto createInstitutions(InstitutionsDto institutionsDto) {
        Institutions byName = institutionsRepo.findByName(institutionsDto.getName());
        if(byName == null){
            try {

                Institutions institutions = Institutions.builder()
                        .name(institutionsDto.getName())
                        .location(institutionsDto.getLocation())
                        .type(institutionsDto.getType())
                        .build();
                Institutions save = institutionsRepo.save(institutions);
                return modelMapper.map(save,InstitutionsDto.class);

            }catch(Exception e){
                throw new RuntimeException("Some information is incorrect");
            }
        }else{
            throw new RuntimeException("Name is Already exist");
        }
    }
}
