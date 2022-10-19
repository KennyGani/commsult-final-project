package id.ac.sgu;

import java.util.concurrent.TimeUnit;

import id.ac.sgu.thermometer.handlers.EventHandler;
import id.ac.sgu.thermometer.listeners.HighTemperatureDetectedListener;
import id.ac.sgu.thermometer.listeners.LowTemperatureDetactedListener;
import id.ac.sgu.thermometer.listeners.NormalTemperatureDetactedListener;
import id.ac.sgu.utilities.RandomSensorNumber;

public class Main {
	private static boolean sensorActive = true;

	public static void main(String[] args) throws InterruptedException {

		RandomSensorNumber sensorNumber = new RandomSensorNumber();
		EventHandler eventHandler = new EventHandler();

		eventHandler.events.subscribe("lowTemperatureDetected", new HighTemperatureDetectedListener());
		eventHandler.events.subscribe("highTemperatureDetected", new LowTemperatureDetactedListener());
		eventHandler.events.subscribe("normalTemperatureDetected", new NormalTemperatureDetactedListener());

		do {
			TimeUnit.SECONDS.sleep(1);

			sensorNumber.getSensorRandomizeNumber();

			// System.out.println(sensorNumber.getTime());
			System.out.println(sensorNumber.getTemperatureNumber());
			// System.out.println(sensorNumber.getAnemoNumber());

			if (sensorNumber.getTemperatureNumber() >= 28) {
				eventHandler.highTemperatureDetected(sensorNumber.getTemperatureNumber());
			}

			if (sensorNumber.getTemperatureNumber() > 15 && sensorNumber.getTemperatureNumber() < 28) {
				eventHandler.normalTemperatureDetected(sensorNumber.getTemperatureNumber());
			}

			if (sensorNumber.getTemperatureNumber() < 15) {
				eventHandler.lowTemperatureDetected(sensorNumber.getTemperatureNumber());
			}

		} while (sensorActive);
	}
}
