package teamup4success.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamup4success.pojos.Subject;
import teamup4success.pojos.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {

    Tutor findById(long id);

}
