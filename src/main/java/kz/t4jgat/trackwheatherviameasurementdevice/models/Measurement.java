package kz.t4jgat.trackwheatherviameasurementdevice.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Measurement {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = -100, message = "value should be greater than -100")
    @Max(value = 100, message = "value should be smaller than 100")
    @NotNull
    @Column(name="value")
    private double value;

    @NotNull
    @Column(name="raining", nullable = false)
    private boolean raining;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sensor_id", referencedColumnName = "id")
    private Sensor sensor;
}
