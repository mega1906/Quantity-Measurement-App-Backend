package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.MeasurementType;
import com.example.demo.model.Unit;

@Service
public class QuantityMeasurementConversionService {

    /**
     * Convert a numeric value from one unit to another.
     * Throws IllegalArgumentException when unit types mismatch.
     */
    public double convert(double value, Unit from, Unit to) {

        if (from.getType() != to.getType()) {
            throw new IllegalArgumentException("Incompatible units");
        }

        if (from.getType() == MeasurementType.TEMPERATURE) {
            // Temperature needs special formula (Celsius <-> Fahrenheit)
            return convertTemperature(value, from, to);
        }

        // Convert using base unit roundtrip for other types
        double baseValue = from.toBase(value);
        return to.fromBase(baseValue);
    }

    /**
     * Handle temperature conversions explicitly.
     */
    private double convertTemperature(double value, Unit from, Unit to) {
        if (from == Unit.CELSIUS && to == Unit.FAHRENHEIT) {
            return (value * 9 / 5) + 32;
        }
        if (from == Unit.FAHRENHEIT && to == Unit.CELSIUS) {
            return (value - 32) * 5 / 9;
        }
        return value;
    }
}