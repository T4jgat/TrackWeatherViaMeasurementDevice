package kz.t4jgat.trackwheatherviameasurementdevice.dto;

import jakarta.validation.constraints.*;
import kz.t4jgat.trackwheatherviameasurementdevice.models.Sensor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementDTO {
    @Min(value = -100, message = "value should be greater than -100")
    @Max(value = 100, message = "value should be smaller than 100")
    @NotNull
    private double value;

    @NotNull(message = "raining should be true or false")
    private boolean raining;

    private SensorDTO sensor;

}
