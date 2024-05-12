package com.example.Stepway.Repository;


import com.example.Stepway.Domain.Enrollment;
import com.example.Stepway.dto.CourseEnrollmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRespository extends JpaRepository<Enrollment,Long> {


    @Query(value = "SELECT COUNT(e.id) FROM Enrollment e " +
            "WHERE e.user_id = :userId",
            nativeQuery = true)
    Long countCurrentUserEnrolledCourses(Long userId);

    @Query(value = "select count(*) from Enrollment", nativeQuery = true)
    Long countTotalEnrollments();

    @Query("SELECT e.courseId.courseName, COUNT(e.id) as enrollmentCount " +
            "FROM Enrollment e " +
            "GROUP BY e.courseId.courseName " +
            "ORDER BY enrollmentCount DESC ")
    List<Object[]> findTop5CourseNamesByEnrollmentCount();


    @Query("SELECT new com.example.Stepway.dto.CourseEnrollmentDTO(" +
            "c.id, c.courseName, COUNT(e)) " +
            "FROM Enrollment e " +
            "JOIN e.courseId c " +
            "GROUP BY c.id, c.courseName " +
            "ORDER BY COUNT(e) DESC")
    List<CourseEnrollmentDTO> findCoursesByEnrollmentCount();

}
