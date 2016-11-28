package pl.edu.utp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.Repository.ConsumptionRepository;

/**
 * Created by Marcin on 26.11.2016.
 */
@RestController
public class ConsumptionController {

    private final ConsumptionRepository consumtpionRepository;

    @Autowired
    public ConsumptionController(ConsumptionRepository consumtpionRepository) {
        this.consumtpionRepository = consumtpionRepository;
    }
}
