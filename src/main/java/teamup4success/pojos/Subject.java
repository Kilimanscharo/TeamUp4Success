package teamup4success.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject {

    @Id
    @GeneratedValue
    Long id;

    String subjectName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    Tutor tutor;

}