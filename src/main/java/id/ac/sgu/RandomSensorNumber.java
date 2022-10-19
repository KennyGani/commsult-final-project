package id.ac.sgu;

import java.time.LocalDateTime;

public class RandomSensorNumber {

    public void getSensorRandomizeNumber() {
        Clock clock = new Clock();
        Thermometer thermometer = new Thermometer();
        Anemometer anemometer = new Anemometer();

        LocalDateTime randomTime = clock.getCurrentTime();
        double randomThermometer = thermometer.getRandomThermometerNumber();
        double randomAnemometer = anemometer.getRandomAnemometerNumber();

        System.out.println(randomTime);
        System.out.println(randomThermometer);
        System.out.println(randomAnemometer);
    }
}
