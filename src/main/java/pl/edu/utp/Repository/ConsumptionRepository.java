package pl.edu.utp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.utp.Entity.Consumption;

/**
 * Created by Marcin on 26.11.2016.
 */
public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
}
