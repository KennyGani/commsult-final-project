package id.ac.sgu;

import java.util.concurrent.TimeUnit;

import id.ac.sgu.airconditioner.AirConditioner;
import id.ac.sgu.airconditioner.handlers.EventHandler;
import id.ac.sgu.airconditioner.listeners.HighTemperatureDetectedListener;
import id.ac.sgu.airconditioner.listeners.LowTemperatureDetactedListener;
import id.ac.sgu.airconditioner.listeners.NormalTemperatureDetactedListener;
import id.ac.sgu.utilities.RandomSensorNumber;

public class Main {
	private static boolean sensorActive = true;

	public static void main(String[] args) throws InterruptedException {

		RandomSensorNumber sensorNumber = new RandomSensorNumber();
		EventHandler eventHandler = new EventHandler();
		AirConditioner airConditioner = new AirConditioner();

		eventHandler.events.subscribe("highTemperatureDetected", new HighTemperatureDetectedListener());
		eventHandler.events.subscribe("lowTemperatureDetected", new LowTemperatureDetactedListener());
		eventHandler.events.subscribe("normalTemperatureDetected", new NormalTemperatureDetactedListener());

		do {
			TimeUnit.SECONDS.sleep(1);

			sensorNumber.getSensorRandomizeNumber();

			if (sensorNumber.getTemperatureNumber() >= 27) {
				eventHandler.highTemperatureDetected(sensorNumber.getTemperatureNumber(), airConditioner);
			}

			if (sensorNumber.getTemperatureNumber() >= 15 &&
					sensorNumber.getTemperatureNumber() < 27) {
				eventHandler.normalTemperatureDetected(sensorNumber.getTemperatureNumber(), airConditioner);
			}

			if (sensorNumber.getTemperatureNumber() < 15) {
				eventHandler.lowTemperatureDetected(sensorNumber.getTemperatureNumber(), airConditioner);
			}

			System.out.println(sensorNumber.getTemperatureNumber());
			System.out.println(airConditioner.getAirConditionerStatus());
			System.out.println(airConditioner.getAirConditionerPowerStatus());

			// // System.out.println(sensorNumber.getTime());

			// System.out.println(sensorNumber.getAnemoNumber());

		} while (sensorActive);
	}
}
