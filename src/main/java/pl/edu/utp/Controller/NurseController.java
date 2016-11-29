package pl.edu.utp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.Entity.Nurse;
import pl.edu.utp.Entity.User;
import pl.edu.utp.Repository.NurseRepository;
import pl.edu.utp.Repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * Created by Marcin on 28.11.2016.
 */
@RestController
public class NurseController {

    private final NurseRepository nurseRepository;
    private final UserRepository userRepository;

    @Autowired
    public NurseController(NurseRepository nurseRepository, UserRepository userRepository) {
        this.nurseRepository = nurseRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/nurse/{login}/{passwd}")
    public Nurse findNurseByLogin(@PathVariable("login") String login, @PathVariable("passwd") String password){
        return nurseRepository.findByLoginAndPassword(login, password);
    }
}
