package kz.t4jgat.trackwheatherviameasurementdevice.utils;

public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(String message) {
        super(message);
    }
}
