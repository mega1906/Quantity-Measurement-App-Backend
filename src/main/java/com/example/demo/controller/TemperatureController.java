package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.QuantityMeasurementConversionService;
import com.example.demo.dto.QuantityDto;
import com.example.demo.model.*;

@RestController
@RequestMapping("/temperature")
/**
 * Controller for temperature-specific conversion endpoints.
 */
public class TemperatureController {

    private static final Logger logger = LoggerFactory.getLogger(TemperatureController.class);

    private final QuantityMeasurementConversionService conversionService;
    
    public TemperatureController(QuantityMeasurementConversionService conversionService) {
    	this.conversionService = conversionService;
    }

    @PostMapping("/convert")
    public double convertTemperature(
            @RequestBody QuantityDto dto,
            @RequestParam Unit targetUnit) {
        logger.info("POST /temperature/convert called with dto={} targetUnit={}", dto, targetUnit);
        return conversionService.convert(
                dto.getValue(), dto.getUnit(), targetUnit);
    }

    @GetMapping("/scales")
    public List<Unit> scales() {
        logger.info("GET /temperature/scales called");
        return List.of(Unit.CELSIUS, Unit.FAHRENHEIT);
    }

//    @GetMapping("/absolute-zero")
//    public double absoluteZeroCelsius() {
//        return -273.15;
//    }
}