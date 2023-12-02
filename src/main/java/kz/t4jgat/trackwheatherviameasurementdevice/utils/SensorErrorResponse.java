package kz.t4jgat.trackwheatherviameasurementdevice.utils;

import lombok.Data;

@Data
public class SensorErrorResponse {
    private String message;
    private long timestamp;

    public SensorErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
