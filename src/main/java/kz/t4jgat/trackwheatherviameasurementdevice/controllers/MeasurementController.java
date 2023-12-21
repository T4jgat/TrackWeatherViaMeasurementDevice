package kz.t4jgat.trackwheatherviameasurementdevice.controllers;

import jakarta.validation.Valid;
import kz.t4jgat.trackwheatherviameasurementdevice.dto.MeasurementDTO;
import kz.t4jgat.trackwheatherviameasurementdevice.dto.SensorDTO;
import kz.t4jgat.trackwheatherviameasurementdevice.models.Measurement;
import kz.t4jgat.trackwheatherviameasurementdevice.models.Sensor;
import kz.t4jgat.trackwheatherviameasurementdevice.services.MeasurementsService;
import kz.t4jgat.trackwheatherviameasurementdevice.services.SensorsService;
import kz.t4jgat.trackwheatherviameasurementdevice.utils.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;


    @Autowired
    public MeasurementController(MeasurementsService measurementsService, ModelMapper modelMapper,
                                 SensorsService sensorsService) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.sensorsService = sensorsService;
    }

    @GetMapping
    public List<MeasurementDTO> findAll() {
        return measurementsService.findAll().stream()
                .map(this::convertToMeasurementDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementDTO> showMeasurement(@PathVariable int id) {
        Measurement measurement = measurementsService.findById(id);

        if (measurement == null)
            throw new MeasurementNotFoundException("404 Not found");

        return ResponseEntity.ok(convertToMeasurementDTO(measurement));
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<RainyDaysResponse> rainyDaysCount() {
        return ResponseEntity.ok(new RainyDaysResponse(measurementsService.calculateRainyDays()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                message.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");

            }
            throw new MeasurementNotCreatedException(message.toString());
        }

        Measurement measurement = convertToMeasurement(measurementDTO);
        Sensor sensor = measurement.getSensor();
        measurement.setSensor(sensor);

        measurementsService.save(measurement);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MeasurementNotCreatedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MeasurementNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(SensorNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        measurementDTO.setSensor(convertToSensorDTO(measurement.getSensor()));
        return measurementDTO;
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
