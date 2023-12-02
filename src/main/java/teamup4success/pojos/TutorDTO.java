package teamup4success.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO {
    String firstname;
    String lastname;
    Double hourlyRate;
    List<String> availableTimes;
    List<Long> subjectIds; // This will contain IDs of subjects that tutor can teach
}
