package pl.edu.utp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Marcin on 26.11.2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consumption {

    @Id
    @GeneratedValue
    @Column(name = "con_id")
    private Long id;
    @OneToOne
    private Product product;
    private Date date;
    private Integer amount;
}
