package teamup4success.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import teamup4success.pojos.Subject;
import teamup4success.pojos.Tutor;
import teamup4success.repository.TutorRepository;
import lombok.RequiredArgsConstructor;

import java.net.URI;
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
    public Tutor getTutorById(@PathVariable(value = "tutorId") long tutorId) {
        return tutorRepository.findById(tutorId);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Tutor> addNewTutor(@RequestBody Tutor tutor) {
        tutor.getSubjectList().forEach(subject -> subject.setTutor(tutor));
        Tutor savedTutor = tutorRepository.save(tutor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTutor.getId())
                .toUri();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .body(savedTutor);
    }

    @GetMapping("/{tutorId}/subjects")
    public List<Subject> getSubjectsByTutor(@PathVariable(value = "tutorId") long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId);
        if (tutor != null) {
            return tutorRepository.findAllSubjectsByTutor(tutor);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor not found");
        }
    }

}
