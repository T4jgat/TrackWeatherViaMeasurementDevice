package kz.t4jgat.trackwheatherviameasurementdevice.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Measurement {
    @Id
    @Column(name="id")
    private int id;

    @Min(value = -100, message = "value should be greater than -100")
    @Max(value = 100, message = "value should be smaller than 100")
    @Column(name="value")
    private double value;

    @Column(name="raining", nullable = false)
    private boolean raining;

    @ManyToOne
    @JoinColumn(name="sensor_id")
    private Sensor sensor;
}
