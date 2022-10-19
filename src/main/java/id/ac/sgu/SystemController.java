package id.ac.sgu;

import java.util.concurrent.TimeUnit;

import id.ac.sgu.airconditioner.AirConditioner;
import id.ac.sgu.airconditioner.acinterface.IAirConditioner;
import id.ac.sgu.airconditioner.handlers.ACEventHandler;
import id.ac.sgu.airconditioner.listeners.HighTemperatureDetectedListener;
import id.ac.sgu.airconditioner.listeners.LowTemperatureDetactedListener;
import id.ac.sgu.airconditioner.listeners.NormalTemperatureDetactedListener;
import id.ac.sgu.heather.listeners.HotWeatherDetactedListener;
import id.ac.sgu.heather.Heather;
import id.ac.sgu.heather.handlers.HeatherEventHandler;
import id.ac.sgu.heather.heatherinterface.IHeather;
import id.ac.sgu.heather.listeners.ColdWeatherDetactedListener;

import id.ac.sgu.utilities.RandomSensorNumber;

public class SystemController {
	private static boolean sensorActive = true;

	public static void main(String[] args) throws InterruptedException {

		RandomSensorNumber sensorNumber = new RandomSensorNumber();
		ACEventHandler acEventHandler = new ACEventHandler();
		HeatherEventHandler heatherEventHandler = new HeatherEventHandler();
		IAirConditioner airConditioner = new AirConditioner();
		IHeather heather = new Heather();

		acEventHandler.events.subscribe("highTemperatureDetected", new HighTemperatureDetectedListener());
		acEventHandler.events.subscribe("lowTemperatureDetected", new LowTemperatureDetactedListener());
		acEventHandler.events.subscribe("normalTemperatureDetected", new NormalTemperatureDetactedListener());
		heatherEventHandler.events.subscribe("coldWeatherDetected", new ColdWeatherDetactedListener());
		heatherEventHandler.events.subscribe("hotWeatherDetected", new HotWeatherDetactedListener());

		do {
			TimeUnit.SECONDS.sleep(2);

			sensorNumber.getSensorRandomizeNumber();

			if (sensorNumber.getTemperatureNumber() >= 27) {
				acEventHandler.highTemperatureDetected(sensorNumber.getTemperatureNumber(), airConditioner);
				heatherEventHandler.hotWeatherDetected(heather);
			}

			if (sensorNumber.getTemperatureNumber() >= 15 &&
					sensorNumber.getTemperatureNumber() < 27) {
				acEventHandler.normalTemperatureDetected(sensorNumber.getTemperatureNumber(), airConditioner);
				heatherEventHandler.hotWeatherDetected(heather);
			}

			if (sensorNumber.getTemperatureNumber() < 15) {
				acEventHandler.lowTemperatureDetected(sensorNumber.getTemperatureNumber(), airConditioner);
				heatherEventHandler.coldWeatherDetected(heather);
			}

			System.out.println("temp : " + sensorNumber.getTemperatureNumber());

			System.out.println(airConditioner.getAirConditionerStatus());
			System.out.println(airConditioner.getAirConditionerPowerStatus());

			System.out.println("");

			System.out.println(heather.getHeatherStatus());
			System.out.println(heather.getHeatherPowerStatus());

			System.out.println("");

			// // System.out.println(sensorNumber.getTime());

			// System.out.println(sensorNumber.getAnemoNumber());

		} while (sensorActive);
	}
}
