package kz.t4jgat.trackwheatherviameasurementdevice.repositories;

import kz.t4jgat.trackwheatherviameasurementdevice.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
