package teamup4success.DB;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import teamup4success.pojos.Tutor;
import teamup4success.repository.TutorRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DBInit {
    private final TutorRepository tutorRepo;

    @PostConstruct
    private void loadTestData() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Load JSON file from the classpath
            InputStream inputStream = new ClassPathResource("/TutorData.json").getInputStream();

            // Parse JSON data
            Tutor[] tutors = mapper.readValue(inputStream, Tutor[].class);

            // Save tutors to the database
            tutorRepo.saveAll(Arrays.asList(tutors));
        } catch (IOException e) {
            System.err.println("Import of tutor data failed");
        }
    }
}
