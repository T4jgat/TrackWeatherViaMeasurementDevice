package kz.t4jgat.trackwheatherviameasurementdevice.services;

import kz.t4jgat.trackwheatherviameasurementdevice.dto.MeasurementDTO;
import kz.t4jgat.trackwheatherviameasurementdevice.models.Measurement;
import kz.t4jgat.trackwheatherviameasurementdevice.models.Sensor;
import kz.t4jgat.trackwheatherviameasurementdevice.repositories.MeasurementsRepository;
import kz.t4jgat.trackwheatherviameasurementdevice.utils.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public Measurement findById(int id) {
        return measurementsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Measurement measurement) {
        Sensor sensor = sensorsService.findByName(measurement.getSensor().getName());
        if (sensor==null)
            throw new SensorNotFoundException("Sensor not found");
        measurement.setSensor(sensor);
        measurementsRepository.save(measurement);
    }

}
