package id.ac.sgu;

public class Thermometer {
    private static double minTemperature = -40;
    private static double maxTemperature = 50;

    public double getRandomThermometerNumber() {
        double temperature = (Math.random() * (maxTemperature - minTemperature + 1) + minTemperature);
        return Math.round(temperature * 10.0) / 10.0;
    }
}
