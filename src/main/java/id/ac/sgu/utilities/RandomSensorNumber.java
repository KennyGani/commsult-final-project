package id.ac.sgu.utilities;

import java.time.LocalTime;

import id.ac.sgu.Clock.Clock;
import id.ac.sgu.anemometer.Anemometer;
import id.ac.sgu.thermometer.Thermometer;

public class RandomSensorNumber {
    private LocalTime time;
    private double temperatureNumber;
    private double anemometerNumber;

    public void getSensorRandomizeNumber() {
        Clock clock = new Clock();
        Thermometer thermometer = new Thermometer();
        Anemometer anemometer = new Anemometer();

        this.time = clock.getCurrentTime();
        this.temperatureNumber = thermometer.getRandomThermometerNumber();
        this.anemometerNumber = anemometer.getRandomAnemometerNumber();
    }

    public LocalTime getTime() {
        return this.time;
    }

    public double getTemperatureNumber() {
        return this.temperatureNumber;
    }

    public double getAnemoNumber() {
        return this.anemometerNumber;
    }
}
