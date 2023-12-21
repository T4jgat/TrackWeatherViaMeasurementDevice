package kz.t4jgat.trackwheatherviameasurementdevice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SensorDTO {
    @Size(min = 2, max = 50, message = "name should be between 2 and 50")
    @NotEmpty(message = "name should not be empty")
    private String name;
}
