package pl.edu.utp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.utp.Entity.Login;

/**
 * Created by Marcin on 25.11.2016.
 */
public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findByLoginAndPasswd(String login, String passwd);

}
