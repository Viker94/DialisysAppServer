package pl.edu.utp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.Entity.Login;
import pl.edu.utp.Repository.LoginRepository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Marcin on 25.11.2016.
 */
@RestController
public class LoginController {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @RequestMapping("/login")
    public List<Login> getAll(){
        return loginRepository.findAll();
    }

    @RequestMapping("/login/{login}/{passwd}")
    public Login findByLoginAndPasswd(@PathVariable("login") String login, @PathVariable("passwd") String passwd){
        return loginRepository.findByLoginAndPasswd(login,passwd);

    }

    //@PostConstruct
    public void addLogin(){
        loginRepository.save(new Login(null, "login", "passwd", 1));
    }
}
