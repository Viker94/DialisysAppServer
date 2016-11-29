package pl.edu.utp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.Entity.Login;
import pl.edu.utp.Entity.User;

/**
 * Created by Marcin on 26.11.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(Login login);

    @Transactional
    @Modifying
    @Query("UPDATE USER_TABLE u SET u.limitPotassium = ?1, u.limitWater = ?2, u.limitSodium = ?3 WHERE id = ?4")
    void updateLimits(double pot, double water, double sodium, Long id);
}
