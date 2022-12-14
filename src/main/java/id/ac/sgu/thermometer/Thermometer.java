package id.ac.sgu.thermometer;

import java.util.Random;

public class Thermometer {
    private static double minTemperature = -20;
    private static double maxTemperature = 40;
    private static double currentTemperature = 26;
    private static double maxTemperatureChangedInSecond = 10;
    private static boolean isTemperatureCreated = false;
    private static boolean decreaseTemperature = false;

    public double getRandomThermometerNumber() {

        Random random = new Random();

        if (!isTemperatureCreated) {
            currentTemperature = Math.random() * (maxTemperature - minTemperature + 1) + minTemperature;
            isTemperatureCreated = true;
        }

        if (maxTemperatureChangedInSecond > 0) {
            maxTemperatureChangedInSecond--;
        } else {
            maxTemperatureChangedInSecond = random.nextInt(10);
            decreaseTemperature = random.nextBoolean();
        }

        if (decreaseTemperature) {
            currentTemperature = currentTemperature - Math.random();
        }
        if (!decreaseTemperature) {
            currentTemperature = currentTemperature + Math.random();
        }

        if (currentTemperature < -20) {
            currentTemperature++;
        }

        if (currentTemperature > 40) {
            currentTemperature--;
        }

        isTemperatureCreated = true;

        return Math.round(currentTemperature * 10.0) / 10.0;
    }
}
