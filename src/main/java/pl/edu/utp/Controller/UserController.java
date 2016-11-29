package pl.edu.utp.Controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.Entity.Consumption;
import pl.edu.utp.Entity.Login;
import pl.edu.utp.Entity.Nurse;
import pl.edu.utp.Entity.User;
import pl.edu.utp.Repository.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Marcin on 26.11.2016.
 */
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final ConsumptionRepository consumptionRepository;
    private final ProductRepository productRepository;
    private final NurseRepository nurseRepository;

    @Autowired
    public UserController(UserRepository userRepository, LoginRepository loginRepository, ConsumptionRepository consumptionRepository, ProductRepository productRepository, NurseRepository nurseRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.consumptionRepository = consumptionRepository;
        this.productRepository = productRepository;
        this.nurseRepository = nurseRepository;
    }

    @RequestMapping("/user/{login}")
    public User findByLogin(@PathVariable("login") Login login){
        return userRepository.findByLogin(login);
    }

    @RequestMapping("/user/{id}/{pot}/{water}/{sodium}")
    public void updateUserLimits(@PathVariable("id") Long id,@PathVariable("pot") double pot, @PathVariable("water") double water, @PathVariable("sodium") double sodium){
        userRepository.updateLimits(pot, water, sodium, id);
    }

    @RequestMapping("/consumption/{userID}/{productID}/{amount}")
    public void addConsumed(@PathVariable("userID") Long userID, @PathVariable("productID") Long productID, @PathVariable("amount") int amount){
        User user = userRepository.getOne(userID);
        System.out.println(user.toString());
        List<Consumption> consumed = user.getConsumed();
        if(consumed == null){
            consumed = new ArrayList<>();
        }
        Consumption con = new Consumption(null, productRepository.findOne(productID), new Date(), amount);
        consumed.add(con);
        consumptionRepository.save(consumed);
        user.setConsumed(consumed);
        userRepository.save(user);
    }

    @RequestMapping("/userAdd/{login}/{passwd}/{firstName}/{lastName}")
    public void addUser(@PathVariable("login") String login, @PathVariable("passwd") String passwd, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        Login l = new Login(null, login, passwd);
        loginRepository.save(l);
        userRepository.save(new User(null, firstName, lastName, 0, 0, 0, 1, 1, 1, l, null));
    }

    @RequestMapping("/userDel/{id}")
    public void userDel(@PathVariable("id") Long id){
        userRepository.delete(id);
    }

    @RequestMapping("/users")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @RequestMapping("/userEdit/{id}/{firstName}/{lastName}/{login}/{passwd}")
    public void userEdit(@PathVariable("id") Long id, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("login") String login, @PathVariable("passwd") String passwd){
        User user = userRepository.findOne(id);
        Login l = user.getLogin();
        l.setLogin(login);
        l.setPasswd(passwd);
        loginRepository.save(l);
        user.setLogin(l);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }

    @PostConstruct
    public void addUserAuto(){
        Login login = new Login(null, "login", "passwd");
        loginRepository.save(login);
        ArrayList<Consumption> consumed = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2016, 11, 28);
        consumed.add(new Consumption(null, productRepository.findOne(new Long(1)), new Date(), 1));
        consumed.add(new Consumption(null, productRepository.findOne(new Long(6)), new Date(), 4));
        consumed.add(new Consumption(null, productRepository.findOne(new Long(7)), cal.getTime(), 1));
        cal.set(2016, 11, 27);
        consumed.add(new Consumption(null, productRepository.findOne(new Long(6)), cal.getTime(), 2));
        cal.set(2016, 11, 26);
        consumed.add(new Consumption(null, productRepository.findOne(new Long(5)), cal.getTime(), 3));
        cal.set(2016, 11, 25);
        consumed.add(new Consumption(null, productRepository.findOne(new Long(4)), cal.getTime(), 4));
        cal.set(2016, 11, 24);
        consumed.add(new Consumption(null, productRepository.findOne(new Long(3)), cal.getTime(), 5));
        cal.set(2016, 11, 23);
        consumed.add(new Consumption(null, productRepository.findOne(new Long(2)), cal.getTime(), 6));
        consumptionRepository.save(consumed);
        User user = new User(null, "Jan", "Kowalski",10,12,30, 100, 100, 100, login, consumed);
        userRepository.save(user);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        nurseRepository.save(new Nurse(null, "Paweł", "Szwed", "pafcio", "sfecio",true, users));
        nurseRepository.save(new Nurse(null, "Piguła2", "Piguła2", "loginPiguła", "asd", false, null));
    }
}
