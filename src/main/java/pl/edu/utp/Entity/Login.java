package pl.edu.utp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Marcin on 25.11.2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Login {

    @Id
    @GeneratedValue
    @Column(name = "login_id")
    private Long id;
    private String login;
    private String passwd;
    private int type;
}
