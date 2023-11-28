package com.example.teamup4success.pojos;

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

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    Tutor tutor;

}