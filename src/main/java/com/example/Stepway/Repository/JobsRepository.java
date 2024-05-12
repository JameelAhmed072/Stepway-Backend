package com.example.Stepway.Repository;

import com.example.Stepway.Domain.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<Jobs,Long> {
}
