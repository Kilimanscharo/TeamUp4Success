package teamup4success.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import teamup4success.pojos.Subject;
import teamup4success.repository.SubjectRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<List<Subject>> addNewSubjects(@RequestBody List<Subject> subjects) {
        List<Subject> savedSubjects = new ArrayList<>();

        for (Subject subject : subjects) {
            // Optional: Add validation logic for each subject
            Subject savedSubject = subjectRepository.save(subject);
            savedSubjects.add(savedSubject);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedSubjects);
    }


}
