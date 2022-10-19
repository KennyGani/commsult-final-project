package id.ac.sgu;

public class Anemometer {
    private static double minAnemo = 0.4;
    private static double maxAnemo = 30;

    public double getRandomAnemometerNumber() {
        double anemometer = (Math.random() * (minAnemo - maxAnemo + 1) + minAnemo);
        return Math.round(anemometer * 10.0) / 10.0;
    }
}
