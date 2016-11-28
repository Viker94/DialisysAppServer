package pl.edu.utp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.Entity.Login;
import pl.edu.utp.Entity.User;
import pl.edu.utp.Repository.LoginRepository;
import pl.edu.utp.Repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Created by Marcin on 26.11.2016.
 */
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public UserController(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @RequestMapping("/user/{login}")
    public User findByLogin(@PathVariable("login") Login login){
        return userRepository.findByLogin(login);
    }

    @RequestMapping("/user/{id}/{pot}/{water}/{sodium}")
    public void updateUserLimits(@PathVariable("id") Long id,@PathVariable("pot") double pot, @PathVariable("water") double water, @PathVariable("sodium") double sodium){
        userRepository.updateLimits(pot, water, sodium, id);
    }

    //@PostConstruct
    public void addUser(){
        Login login = new Login(null, "login", "passwd", 1);
        loginRepository.save(login);
        User user = new User(null, "Jan", "Kowalski",10,12,30, 100, 100, 100, login);
        userRepository.save(user);
    }
}
