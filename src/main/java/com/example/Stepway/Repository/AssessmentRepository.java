package com.example.Stepway.Repository;

import com.example.Stepway.Domain.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Long> {


    @Query("SELECT a FROM Assessment a WHERE a.courseId.id = :courseId")
    List<Assessment> findByCourseId(Long courseId);
}
