package teamup4success.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import teamup4success.pojos.Subject;
import teamup4success.pojos.Tutor;
import teamup4success.pojos.TutorDTO;
import teamup4success.repository.SubjectRepository;
import teamup4success.repository.TutorRepository;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorRepository tutorRepository;
    private final SubjectRepository subjectRepository;

    @GetMapping
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    @GetMapping("/{tutorId}")
    public Tutor getTutorById(@PathVariable(value = "tutorId") long tutorId) {
        return tutorRepository.findById(tutorId);
    }


    @PostMapping
    public ResponseEntity<Tutor> addTutorWithSubjects(@RequestBody TutorDTO tutorDTO) {
        // Find the subjects by their IDs
        List<Subject> subjects = subjectRepository.findAllById(tutorDTO.getSubjectIds());

        Tutor tutor = new Tutor();
        tutor.setFirstname(tutorDTO.getFirstname());
        tutor.setLastname(tutorDTO.getLastname());
        tutor.setHourlyRate(tutorDTO.getHourlyRate());
        tutor.setAvailableTimes(tutorDTO.getAvailableTimes());
        tutor.setSubjectList(subjects);

        // Save the tutor entity
        Tutor savedTutor = tutorRepository.save(tutor);

        return new ResponseEntity<>(savedTutor, HttpStatus.CREATED);
    }

    @GetMapping("/{tutorId}/subjects")
    public ResponseEntity<List<Subject>> getSubjectsByTutor(@PathVariable(value = "tutorId") long tutorId) {
        // Use the findById method from JpaRepository
        Tutor tutor = tutorRepository.findById(tutorId);

        if (tutor == null) {
            return ResponseEntity.notFound().build();
        }

        List<Subject> subjects = tutor.getSubjectList();
        return ResponseEntity.ok(subjects);
    }


}
