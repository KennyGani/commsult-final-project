package id.ac.sgu;
import java.util.concurrent.TimeUnit;

public class Main {
	private static boolean sensorActive = true;

	public static void main(String[] args) throws InterruptedException {
		RandomSensorNumber sensorNumber = new RandomSensorNumber();

		do {
			TimeUnit.SECONDS.sleep(1);

			sensorNumber.getSensorRandomizeNumber();

			System.out.println(sensorNumber.getTime());
			System.out.println(sensorNumber.getThermoNumber());
			System.out.println(sensorNumber.getAnemoNumber());
		} while (sensorActive);
	}
}
