package pl.edu.utp.Controller;

import net.minidev.json.JSONObject;
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
import java.util.List;

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

    @RequestMapping("/nurseLogin/{login}/{passwd}")
    public Nurse findNurseByLogin(@PathVariable("login") String login, @PathVariable("passwd") String password){
        return nurseRepository.findByLoginAndPassword(login, password);
    }

    @RequestMapping("/nurse/{firstName}/{lastName}/{login}/{passwd}")
    public void addNurse(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("login") String login, @PathVariable("passwd") String passwd){
        nurseRepository.save(new Nurse(null, firstName, lastName, login, passwd, false, null));
    }
    @RequestMapping("/nurse")
    public List<Nurse> findAll(){
        return nurseRepository.findAll();
    }

    @RequestMapping("/nurseAddPatient/{nurseID}/{userID}")
    public void updatePatients(@PathVariable("nurseID") Long nurseID, @PathVariable("userID") Long userID){
        Nurse nurse = nurseRepository.getOne(nurseID);
        List<User> patients = nurse.getPatients();
        if(patients == null){
            patients = new ArrayList<User>();
        }
        patients.add(userRepository.findOne(userID));
        nurse.setPatients(patients);
        nurseRepository.save(nurse);
    }

    @RequestMapping("/nurseDel/{id}")
    public void nurseDel(@PathVariable("id") Long id){
        nurseRepository.delete(id);
    }

    @RequestMapping("/nurseNumPatients/{id}")
    public JSONObject nurseNumPatients(@PathVariable("id") Long id){
        List<User> patients = nurseRepository.findOne(id).getPatients();
        JSONObject json = new JSONObject();
        if(patients == null){
            json.put("size", 0);
            return json;
        }
        else {
            json.put("size", patients.size());
            return json;
        }
    }

    @RequestMapping("/nurseEdit/{id}/{firstName}/{lastName}/{login}/{passwd}")
    public void editNurse(@PathVariable("id") Long id, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("login") String login, @PathVariable("passwd") String passwd){
        Nurse nurse = nurseRepository.findOne(id);
        nurse.setFirstName(firstName);
        nurse.setLastName(lastName);
        nurse.setLogin(login);
        nurse.setPassword(passwd);
        nurseRepository.save(nurse);
    }

    @RequestMapping("/nurseDelPatients/{id}")
    public void nurseDelPatients(@PathVariable("id") Long id){
        Nurse nurse = nurseRepository.findOne(id);
        nurse.setPatients(new ArrayList<>());
        nurseRepository.save(nurse);
    }

    @RequestMapping("/nursePatients/{id}")
    public List<User> nursePatients(@PathVariable("id") Long id){
        return nurseRepository.findOne(id).getPatients();
    }

    @RequestMapping("/nurseDelOnePatient/{nurseID}/{userID}")
    public void delOnePatient(@PathVariable("nurseID") Long nurseID, @PathVariable("userID") Long userID){
        Nurse nurse = nurseRepository.findOne(nurseID);
        List<User> patients = nurse.getPatients();
        User user = userRepository.findOne(userID);
        patients.removeIf(p -> p.equals(user));
        nurse.setPatients(patients);
        nurseRepository.save(nurse);
    }
}
