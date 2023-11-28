package com.example.teamup4success.controller;

import com.example.teamup4success.pojos.Tutor;
import com.example.teamup4success.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorRepository tutorRepository;

    @GetMapping
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    @GetMapping("/{tutorId}")
    public Tutor getTutorById(@PathVariable(value = "tutorId") Long tutorId) {
        return tutorRepository.findById(tutorId).orElse(null);
    }

}
