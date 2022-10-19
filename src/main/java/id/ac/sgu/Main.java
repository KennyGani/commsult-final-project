package id.ac.sgu;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Main {
	private static boolean sensorActive = true;

	public static void main(String[] args) throws InterruptedException {
		RandomSensorNumber sensorNumber = new RandomSensorNumber();

		do {
			TimeUnit.SECONDS.sleep(1);

			Map<String, Object> map = sensorNumber.getSensorRandomizeNumber();
			
			System.out.println(map.get("time"));
			System.out.println(map.get("anemoNumber"));
			System.out.println(map.get("thermoNumber"));
		} while (sensorActive);
	}
}
