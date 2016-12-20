package pl.edu.utp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Marcin on 26.11.2016.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "USER_TABLE")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String firstName;
    private String lastName;
    private double potassium;
    private double water;
    private double sodium;
    private double limitPotassium;
    private double limitWater;
    private double limitSodium;
    private Date nextVisit;
    @OneToOne
    private Login login;
    @OneToMany(orphanRemoval = true)
    private List<Consumption> consumed;
}
