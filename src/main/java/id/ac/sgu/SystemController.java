package id.ac.sgu;

import java.util.concurrent.TimeUnit;

import id.ac.sgu.airconditioner.AirConditionerController;
import id.ac.sgu.airconditioner.acinterface.IAirConditioner;
import id.ac.sgu.airconditioner.handlers.ACEventHandler;
import id.ac.sgu.airconditioner.listeners.HighTemperatureDetectedListener;
import id.ac.sgu.airconditioner.listeners.LowTemperatureDetactedListener;
import id.ac.sgu.airconditioner.listeners.NormalTemperatureDetactedListener;
import id.ac.sgu.airconditioner.model.AirConditionerModel;
import id.ac.sgu.heather.listeners.HotWeatherDetactedListener;
import id.ac.sgu.heather.model.HeatherModel;
import id.ac.sgu.heather.HeatherController;
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

		AirConditionerModel airConditioner = new AirConditionerModel();
		IAirConditioner iAirConditioner = new AirConditionerController(airConditioner);

		HeatherModel heatherModel = new HeatherModel();
		IHeather iHeather = new HeatherController(heatherModel);

		acEventHandler.events.subscribe("highTemperatureDetected", new HighTemperatureDetectedListener());
		acEventHandler.events.subscribe("lowTemperatureDetected", new LowTemperatureDetactedListener());
		acEventHandler.events.subscribe("normalTemperatureDetected", new NormalTemperatureDetactedListener());

		heatherEventHandler.events.subscribe("coldWeatherDetected", new ColdWeatherDetactedListener());
		heatherEventHandler.events.subscribe("hotWeatherDetected", new HotWeatherDetactedListener());

		do {
			TimeUnit.SECONDS.sleep(1);

			sensorNumber.getSensorRandomizeNumber();

			if (sensorNumber.getTemperatureNumber() >= 27) {
				acEventHandler.highTemperatureDetected(sensorNumber.getTemperatureNumber(), iAirConditioner);
				heatherEventHandler.hotWeatherDetected(iHeather);
			}

			if (sensorNumber.getTemperatureNumber() >= 15 &&
					sensorNumber.getTemperatureNumber() < 27) {
				acEventHandler.normalTemperatureDetected(sensorNumber.getTemperatureNumber(), iAirConditioner);
				heatherEventHandler.hotWeatherDetected(iHeather);
			}

			if (sensorNumber.getTemperatureNumber() < 15) {
				acEventHandler.lowTemperatureDetected(sensorNumber.getTemperatureNumber(), iAirConditioner);
				heatherEventHandler.coldWeatherDetected(iHeather);
			}

			System.out.println("Temp : " + sensorNumber.getTemperatureNumber());

			System.out.println("AC");
			System.out.println("-" + iAirConditioner.getAirConditionerStatus());
			System.out.println("-" + iAirConditioner.getAirConditionerPowerStatus());

			System.out.println("Heather");
			System.out.println("-" + iHeather.getHeatherStatus());
			System.out.println("-" + iHeather.getHeatherPowerStatus());

			System.out.println("");

			// // System.out.println(sensorNumber.getTime());

			// System.out.println(sensorNumber.getAnemoNumber());

		} while (sensorActive);
	}
}
