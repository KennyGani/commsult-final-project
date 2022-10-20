package id.ac.sgu.raindropsensor;

import java.util.Random;

public class RainDropSensor {

    private static int rainStatusChangeInEveryRandomSecond = 10;
    private static boolean isRaining = false;

    public boolean getRainDropSensorStatus() {

        Random random = new Random();

        if (rainStatusChangeInEveryRandomSecond > 0) {
            rainStatusChangeInEveryRandomSecond--;
        } else {
            rainStatusChangeInEveryRandomSecond = random.nextInt(10);
            isRaining = random.nextBoolean();
        }

        return isRaining;
    }
}
