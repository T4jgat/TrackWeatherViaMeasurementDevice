package kz.t4jgat.trackwheatherviameasurementdevice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import kz.t4jgat.trackwheatherviameasurementdevice.models.Measurement;
import lombok.Data;

import java.util.List;

@Data
public class SensorDTO {
    @Size(min = 2, max = 50, message = "name should be between 2 and 50")
    @NotEmpty(message = "name should not be empty")
    private String name;

//    @NotEmpty(message = "measurements should not be empty")
//    private List<Measurement> measurements;
}
