package teamup4success.pojos;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tutor {

    @Id
    @GeneratedValue
    Long id;

    String firstname;
    String lastname;
    Double hourlyRate;

    @ElementCollection
    List<String> availableTimes;

    @OneToMany(mappedBy = "tutor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Subject> subjectList;

}
