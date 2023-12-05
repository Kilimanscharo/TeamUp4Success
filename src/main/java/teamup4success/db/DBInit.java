package teamup4success.db;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import teamup4success.pojos.Subject;
import teamup4success.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DBInit {
    private final SubjectRepository subRepo;

    @PostConstruct
    public void testdata() {
        List<Subject> subjects = new ArrayList<>();
        
        subjects.add(new Subject(1, "Physics", null));
        subjects.add(new Subject(2, "Mathematics", null));
        subjects.add(new Subject(3, "German", null));
        subjects.add(new Subject(4, "English", null));
        subjects.add(new Subject(5, "History", null));
        subRepo.saveAll(subjects);
    }
}
