package kz.t4jgat.trackwheatherviameasurementdevice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import kz.t4jgat.trackwheatherviameasurementdevice.models.Sensor;
import lombok.Data;

@Data
public class MeasurementDTO {
    @Min(value = -100, message = "value should be greater than -100")
    @Max(value = 100, message = "value should be smaller than 100")
    @NotEmpty(message = "value should not be empty")
    private double value;

    @NotEmpty(message = "raining value should not be empty")
    private boolean raining;

    @NotEmpty(message = "sensor should not be empty")
    private Sensor sensor;

}
