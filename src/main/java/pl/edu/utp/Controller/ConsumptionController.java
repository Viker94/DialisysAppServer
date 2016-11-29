package pl.edu.utp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.Entity.Consumption;
import pl.edu.utp.Repository.ConsumptionRepository;

import java.util.List;

/**
 * Created by Marcin on 26.11.2016.
 */
@RestController
public class ConsumptionController {

    private final ConsumptionRepository consumtpionRepository;

    @RequestMapping("/consumption")
    public List<Consumption> findAll(){
        return consumtpionRepository.findAll();
    }

    @Autowired
    public ConsumptionController(ConsumptionRepository consumtpionRepository) {
        this.consumtpionRepository = consumtpionRepository;
    }
}
