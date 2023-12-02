package teamup4success.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject {

    @Id
    Long id;

    String subjectName;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjectList")
    List<Tutor> tutors;
}