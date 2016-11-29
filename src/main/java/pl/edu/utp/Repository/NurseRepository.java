package pl.edu.utp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.Entity.Nurse;
import pl.edu.utp.Entity.User;

import java.util.List;

/**
 * Created by Marcin on 28.11.2016.
 */
public interface NurseRepository extends JpaRepository<Nurse, Long>{

    Nurse findByLoginAndPassword(String login, String password);
}
