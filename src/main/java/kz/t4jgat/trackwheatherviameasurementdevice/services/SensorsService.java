package kz.t4jgat.trackwheatherviameasurementdevice.services;

import kz.t4jgat.trackwheatherviameasurementdevice.models.Sensor;
import kz.t4jgat.trackwheatherviameasurementdevice.repositories.SensorsRepository;
import kz.t4jgat.trackwheatherviameasurementdevice.utils.SensorNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    public Sensor findById(int id) {
        return sensorsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {

        if (findById(sensor.getId())!=null) {
            throw new SensorNotCreatedException("This sensor is already exists");
        }

        sensorsRepository.save(sensor);
    }

    public Sensor findByName(String sensorName) {
        return sensorsRepository.findAllByName(sensorName);
    }
}
