package com.example.OnlineCourses.repositories;

import com.example.OnlineCourses.domains.UDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UDetailsRepository extends JpaRepository<UDetails, Long> {

    Optional<UDetails> findUDetailsByEmail(String email);

}
