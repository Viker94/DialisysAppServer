package pl.edu.utp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.utp.Entity.Nurse;

/**
 * Created by Marcin on 28.11.2016.
 */
public interface NurseRepository extends JpaRepository<Nurse, Long>{

    Nurse findByLoginAndPassword(String login, String password);
}
