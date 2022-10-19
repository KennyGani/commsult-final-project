package id.ac.sgu.utilities;

import java.time.LocalTime;

import id.ac.sgu.Anemometer;
import id.ac.sgu.Clock;
import id.ac.sgu.thermometer.Thermometer;

public class RandomSensorNumber {
    private LocalTime time;
    private double thermoNumber;
    private double anemoNumber;

    public void getSensorRandomizeNumber() {
        Clock clock = new Clock();
        Thermometer thermometer = new Thermometer();
        Anemometer anemometer = new Anemometer();

        this.time = clock.getCurrentTime();
        this.thermoNumber = thermometer.getRandomThermometerNumber();
        this.anemoNumber = anemometer.getRandomAnemometerNumber();
    }

    public LocalTime getTime() {
        return this.time;
    }

    public double getTemperatureNumber() {
        return this.thermoNumber;
    }

    public double getAnemoNumber() {
        return this.anemoNumber;
    }
}
