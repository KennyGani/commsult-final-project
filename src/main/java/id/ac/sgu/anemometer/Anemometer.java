package id.ac.sgu.anemometer;

import java.util.Random;

public class Anemometer {
    private static double minAnemometer = 0.4;
    private static double maxAnemometer = 30;
    private static double currentAnemometer = 15;
    private static double maxAnemometerChangedInSecond = 10;
    private static boolean isAnemometerCreated = false;
    private static boolean decreaseAnemometer = false;

    public double getRandomAnemometerNumber() {

        Random random = new Random();

        if (!isAnemometerCreated) {
            currentAnemometer = Math.random() * (maxAnemometer - minAnemometer + 1) + minAnemometer;
            isAnemometerCreated = true;
        }

        if (maxAnemometerChangedInSecond > 0) {
            maxAnemometerChangedInSecond--;
        } else {
            maxAnemometerChangedInSecond = random.nextInt(10);
            decreaseAnemometer = random.nextBoolean();
        }

        if (decreaseAnemometer) {
            currentAnemometer = currentAnemometer - Math.random();
        }
        if (!decreaseAnemometer) {
            currentAnemometer = currentAnemometer + Math.random();
        }

        if (currentAnemometer < 0.3) {
            currentAnemometer++;
        }

        if (currentAnemometer > 30) {
            currentAnemometer--;
        }

        isAnemometerCreated = true;

        return Math.round(currentAnemometer * 10.0) / 10.0;
    }
}
