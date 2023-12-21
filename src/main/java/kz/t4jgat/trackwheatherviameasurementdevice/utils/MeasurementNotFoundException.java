package kz.t4jgat.trackwheatherviameasurementdevice.utils;

public class MeasurementNotFoundException extends RuntimeException {
    public MeasurementNotFoundException(String message) {
        super(message);
    }
}
