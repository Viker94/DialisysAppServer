package pl.edu.utp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 28.11.2016.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Nurse {

    @Id
    @GeneratedValue
    @Column(name = "nurse_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Boolean admin;
    @OneToMany
    private List<User> patients;
}
