package com.example.Stepway.Repository;

import com.example.Stepway.Domain.Institutions;
import com.example.Stepway.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionsRepo extends JpaRepository<com.example.Stepway.Domain.Institutions,Long> {

    Institutions findByName(String name);
}
