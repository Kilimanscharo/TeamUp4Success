package com.example.teamup4success.repository;

import com.example.teamup4success.pojos.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Tutor findById(long id);
}
