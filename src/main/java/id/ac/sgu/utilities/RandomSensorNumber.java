package id.ac.sgu.utilities;

import java.time.LocalTime;

import id.ac.sgu.anemometer.Anemometer;
import id.ac.sgu.clock.Clock;
import id.ac.sgu.raindropsensor.RainDropSensor;
import id.ac.sgu.thermometer.Thermometer;

public class RandomSensorNumber {
    private LocalTime time;
    private double temperatureNumber;
    private double anemometerNumber;
    private boolean rainDropSensor;

    public void getSensorRandomizeNumber() {
        Clock clock = new Clock();
        Thermometer thermometer = new Thermometer();
        Anemometer anemometer = new Anemometer();
        RainDropSensor rainDropSensor = new RainDropSensor();

        this.time = clock.getCurrentTime();
        this.temperatureNumber = thermometer.getRandomThermometerNumber();
        this.anemometerNumber = anemometer.getRandomAnemometerNumber();
        this.rainDropSensor = rainDropSensor.getRainDropSensorStatus();
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

    public boolean getRainDropSensorStatus() {
        return this.rainDropSensor;
    }
}
