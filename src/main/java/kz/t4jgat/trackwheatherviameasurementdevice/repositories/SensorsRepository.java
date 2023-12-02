package kz.t4jgat.trackwheatherviameasurementdevice.repositories;

import kz.t4jgat.trackwheatherviameasurementdevice.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
}
