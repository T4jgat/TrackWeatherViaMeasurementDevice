package kz.t4jgat.trackwheatherviameasurementdevice.controllers;

import jakarta.validation.Valid;
import kz.t4jgat.trackwheatherviameasurementdevice.dto.SensorDTO;
import kz.t4jgat.trackwheatherviameasurementdevice.models.Sensor;
import kz.t4jgat.trackwheatherviameasurementdevice.services.SensorsService;
import kz.t4jgat.trackwheatherviameasurementdevice.utils.SensorErrorResponse;
import kz.t4jgat.trackwheatherviameasurementdevice.utils.SensorNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorsService sensorsService, ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public List<SensorDTO> sensorList() {
        return sensorsService.findAll().stream().map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SensorDTO sensor(@PathVariable int id) {
        return convertToPersonDTO(sensorsService.findById(id));
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> sensorRegistration(@RequestBody @Valid SensorDTO sensorDTO,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                message.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new SensorNotCreatedException(message.toString());
        }

        sensorsService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse errorResponse = new SensorErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private SensorDTO convertToPersonDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
