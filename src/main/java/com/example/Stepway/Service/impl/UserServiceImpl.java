package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Role;
import com.example.Stepway.Domain.User;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.RoleRepository;
import com.example.Stepway.Repository.UserRepository;
import com.example.Stepway.Repository.UserSpecification;
import com.example.Stepway.Service.UserService;
import com.example.Stepway.dto.SearchCriteria;
import com.example.Stepway.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EntityManager entityManager;  // entity manager gives us criteria builder
    @Autowired
    PasswordEncoder passwordEncoder;
    //   I have made this constuctor for testing purpose
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
    }
    //  =================================>    this code for the criteria builder

//    public List<User> getUserWithFilters(String firstName,String lastName){
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
//        Root<User>  userRoot = cq.from(User.class);
//        List<Predicate> predicates = new ArrayList<>();
//        if(firstName != null){
//            predicates.add(criteriaBuilder.like(userRoot.get("firstName"),firstName));
//        }
//        if(lastName != null){
//            predicates.add(criteriaBuilder.like(userRoot.get("lastName"),firstName));
//        }
//
//        cq.where(predicates.toArray(new Predicate[predicates.size()]));
//        TypedQuery<User> query = entityManager.createQuery(cq);
//        return query.getResultList();
//    }
//  ======================>>>>>>>>>>>>>>>




    public List<User> getSearchdUser(SearchCriteria searchCriteria){
        try {
            UserSpecification userSpecification = new UserSpecification(searchCriteria);
            List<User> users = userRepository.findAll(userSpecification);
            return users;
        }
        catch (Exception e){
            throw new RuntimeException("No Employee Exist "+e);
        }
    }
    @Override
    public List<UserDto> getAllUser(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize, sort);

        Page<User> pageUser = userRepository.findAll(p);
        List<User> allUsers = pageUser.getContent();
        return allUsers.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User userByEmail = userRepository.findByEmail(userDto.getEmail());
        if(userByEmail == null){
            try {
                Optional<Role> roles = Optional.ofNullable(roleRepository
                        .findByName(userDto.getRole())
                        .orElseThrow(() -> new RuntimeException("Role is incorrect")));
                Set<Role> rolesList = new HashSet<>();
                rolesList.add(roles.get());

                User user = User.builder()
                        .firstName(userDto.getFirstName())
                        .lastName(userDto.getLastName())
                        .password(passwordEncoder.encode(userDto.getPassword()))
                        .role(rolesList)
                        .phoneNumber(userDto.getPhoneNumber())
                        .email(userDto.getEmail())
                        .gender(userDto.getGender())
                        .build();
                User save = userRepository.save(user);
                return modelMapper.map(save,UserDto.class);

            }catch(Exception e){
                throw new RuntimeException("Some information is incorrect");
            }
        }else{
            throw new RuntimeException("Email is Already exist");
        }
    }

    @Override
    public UserDto getUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        User user = optionalUser.orElseThrow(()->new ResourceNotFound("User not found with the id : "+ id));

        return modelMapper.map(user,UserDto.class);
    }
    public Long countStudentsWithRoleStudent() {
        Long optionalUser = userRepository.countUsersWithRoleStudent();


        return optionalUser;
    }
    public Long countTeachers() {
        Long optionalUser = userRepository.countUsersWithRoleTeacher();
        return optionalUser;
    }

    public Long countMaleStudents(){
        Long maleStudents = userRepository.countMaleStudents();
        return maleStudents;
    }
    public Long countFemaleStudents(){
        Long femlaeStudents = userRepository.countFemaleStudents();
        return femlaeStudents;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            try {
                Optional<Role> roles = Optional.ofNullable(roleRepository
                        .findByName(userDto.getRole())
                        .orElseThrow(() -> new RuntimeException("Role is incorrect")));

                Set<Role> rolesList = new HashSet<>();
                rolesList.add(roles.get());

                User user1 = User.builder()
                        .firstName(userDto.getFirstName())
                        .lastName(userDto.getLastName())
                        .email(userDto.getEmail())
                        .password(userDto.getPassword())
                        .role(rolesList)
                        .phoneNumber(userDto.getPhoneNumber())
                        .gender(userDto.getGender())
                        .build();
                User save = userRepository.save(user1);
                return modelMapper.map(save, UserDto.class);
            } catch (Exception e) {
                throw new RuntimeException("Some information is incorrect");
            }
        }
        throw new RuntimeException("User Not Found");
    }

    @Override
    public void deleteUser(Long id) {

        if(!userRepository.existsById(id)){
            throw new ResourceNotFound("User not found with Id : "+ id);
        }
        userRepository.deleteById(id);
    }

    public List<UserDto> findStudent(Integer pageNumber, Integer pageSize,String sortBy,String sortDir){
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize, sort);
        String role = "ROLE_STUDENT";
        Page<User> pageStudents = userRepository.findUsersWithRoleStudent(role,p);
        List<User> allStudents = pageStudents.getContent();

        return allStudents.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());

    }
    public List<UserDto> findTeachers(Integer pageNumber, Integer pageSize,String sortBy,String sortDir){

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize, sort);
        String role = "ROLE_TEACHER";

        Page<User> pageTeachers = userRepository.findUsersWithRoleTeacher(role,p);
        List<User> allTeachers = pageTeachers.getContent();
        return allTeachers.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());

    }

    public String getUserByName(long id){
        return userRepository.getLoginName(id);
    }


    public List<UserDto> searchFirstName(String firstName){
        List<User> users = this.userRepository.findByFirstNameContaining(firstName);
        List<UserDto> collect = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return collect;
    }

//    public List<UserDto> findCourseNamesByTeacherId(Long userId){
//        List<String> courseNamesByTeacherId = this.userRepository.findCourseNamesByTeacherId(userId);
//        List<UserDto> collect = courseNamesByTeacherId.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
//        return collect;
//    }






}
