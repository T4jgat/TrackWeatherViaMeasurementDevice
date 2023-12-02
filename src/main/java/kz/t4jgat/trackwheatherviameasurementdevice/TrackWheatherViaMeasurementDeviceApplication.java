package kz.t4jgat.trackwheatherviameasurementdevice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrackWheatherViaMeasurementDeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackWheatherViaMeasurementDeviceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
