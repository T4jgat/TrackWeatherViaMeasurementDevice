package kz.t4jgat.trackwheatherviameasurementdevice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Size(min = 2, max = 50, message = "name should be between 1 and 50")
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

}
