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
    public ResponseEntity<Tutor> addTutorWithSubjects(@RequestBody Tutor tutor) {

        // Extract IDs from the subject list
        List<Integer> subjectIds = tutor.getSubjectList().stream()
                .map(Subject::getId)
                .collect(Collectors.toList());

        // Find the subjects from the database using the extracted IDs
        List<Subject> subjects = subjectRepository.findAllById(subjectIds);

        // Create a new Tutor object and set its properties
        Tutor newTutor = new Tutor();
        newTutor.setFirstname(tutor.getFirstname());
        newTutor.setLastname(tutor.getLastname());
        newTutor.setHourlyRate(tutor.getHourlyRate());
        newTutor.setAvailableTimes(tutor.getAvailableTimes());
        newTutor.setSubjectList(subjects);

        // Save the new Tutor object to the database
        Tutor savedTutor = tutorRepository.save(newTutor);

        // Return the saved Tutor object in the response
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
