package com.example.demo.model;

/**
 * Unit enum defines available units and base conversion factors.
 *
 * For non-temperature types this enum uses linear scaling via toBase/fromBase.
 * Temperature conversions are handled separately in the service layer.
 */
public enum Unit {
    // Length
    METER(MeasurementType.LENGTH, 1.0),
    CENTIMETER(MeasurementType.LENGTH, 0.01),
    KILOMETER(MeasurementType.LENGTH, 1000.0),

    // Weight
    GRAM(MeasurementType.WEIGHT, 1.0),
    KILOGRAM(MeasurementType.WEIGHT, 1000.0),

    // Volume
    LITER(MeasurementType.VOLUME, 1.0),
    MILLILITER(MeasurementType.VOLUME, 0.001),

    // Temperature 
    CELSIUS(MeasurementType.TEMPERATURE, 1.0),
    FAHRENHEIT(MeasurementType.TEMPERATURE, 1.0);

    private final MeasurementType type;
    private final double toBaseFactor;

    Unit(MeasurementType type, double toBaseFactor) {
        this.type = type;
        this.toBaseFactor = toBaseFactor;
    }

    public MeasurementType getType() {
        return type;
    }

    public double toBase(double value) {
        return value * toBaseFactor;
    }

    public double fromBase(double baseValue) {
        return baseValue / toBaseFactor;
    }
}