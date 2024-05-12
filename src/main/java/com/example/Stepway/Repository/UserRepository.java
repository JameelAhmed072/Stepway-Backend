package com.example.Stepway.Repository;

import com.example.Stepway.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    User findByEmail(String email);

    @Query(value = "SELECT u.* FROM User u " +
            "INNER JOIN user_roles ur ON u.id = ur.user_id " +
            "INNER JOIN Role r ON ur.role_id = r.id " +
            "WHERE r.name = :role " ,
            countQuery = "SELECT count(u.id) FROM User u " +
                    "INNER JOIN user_roles ur ON u.id = ur.user_id " +
                    "INNER JOIN Role r ON ur.role_id = r.id " +
                    "WHERE r.name = :role ",
            nativeQuery = true)
    Page<User> findUsersWithRoleStudent(String role,Pageable pageable);
    @Query(value = "SELECT u.* FROM User u " +
            "INNER JOIN user_roles ur ON u.id = ur.user_id " +
            "INNER JOIN Role r ON ur.role_id = r.id " +
            "WHERE r.name = :role " ,
            countQuery = "SELECT count(u.id) FROM User u " +
                    "INNER JOIN user_roles ur ON u.id = ur.user_id " +
                    "INNER JOIN Role r ON ur.role_id = r.id " +
                    "WHERE r.name = :role ",
            nativeQuery = true)
    Page<User> findUsersWithRoleTeacher(String role,Pageable pageable);

    @Query(value = "SELECT COUNT(u.id) FROM User u " +
            "INNER JOIN user_roles ur ON u.id = ur.user_id " +
            "INNER JOIN Role r ON ur.role_id = r.id " +
            "WHERE r.name = 'ROLE_STUDENT'",
            nativeQuery = true)
    Long countUsersWithRoleStudent();
//    List<User> getUserWithFilters(String firstName,String lastName);

    @Query(value = "SELECT COUNT(u.id) FROM User u " +
            "INNER JOIN user_roles ur ON u.id = ur.user_id " +
            "INNER JOIN Role r ON ur.role_id = r.id " +
            "WHERE r.name = 'ROLE_TEACHER'",
            nativeQuery = true)
    Long countUsersWithRoleTeacher();

    @Query(value = "select count(u.id) from User u " +
            "inner join user_roles ur on u.id = ur.user_id " +
            "inner join Role r on ur.role_id = r.id " +
            "where r.name = 'ROLE_STUDENT' and u.gender = 'Male'", nativeQuery = true)
    Long countMaleStudents();
    @Query(value = "select count(u.id) from User u " +
            "inner join user_roles ur on u.id = ur.user_id " +
            "inner join Role r on ur.role_id = r.id " +
            "where r.name = 'ROLE_STUDENT' and u.gender = 'Female'", nativeQuery = true)
    Long countFemaleStudents();


    @Query(value = "SELECT FIRST_NAME FROM USER WHERE ID = ?", nativeQuery = true)
    public String getLoginName(Long id);


    List<User> findByFirstNameContaining(String firstName);

//    @Query(value = "SELECT c.courseName FROM Course c " +
//            "INNER JOIN Enrollment e ON e.course_id = c.id " +
//            "INNER JOIN User u ON u.id = e.user_id " +
//            "INNER JOIN UserRoles ur ON ur.user_id = u.id " +
//            "INNER JOIN Role r ON r.id = ur.role_id " +
//            "WHERE r.name = 'ROLE_TEACHER' AND u.id = ?1")
//    List<String> findCourseNamesByTeacherId(Long userId);


}
