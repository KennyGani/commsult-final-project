package id.ac.sgu;

import java.time.LocalTime;
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
import id.ac.sgu.window.WindowController;
import id.ac.sgu.window.model.WindowModel;
import id.ac.sgu.window.windowinterface.IWindow;

public class SystemController {
	private static boolean sensorActive = true;
	private static boolean isManual = true;
	private static boolean isRaining;
	private static boolean isHeavyWind;
	private static boolean isNight;

	private static double currentTemperatureNumber = 20;
	private static double currentAnemometerNumber = 25;
	private static boolean currentRainingStatus = false;
	private static LocalTime currentTime = LocalTime.parse("12:00:00");

	public void setManualTemperature(double temperatureNumber) {
		SystemController.currentTemperatureNumber = temperatureNumber;
	}

	public void setManualAnemometer(double anemometerNumber) {
		SystemController.currentAnemometerNumber = anemometerNumber;
	}

	public void setManualRainingStatus(Boolean rainingStatus) {
		SystemController.currentRainingStatus = rainingStatus;
	}

	public void setCurrentTme(LocalTime time) {
		SystemController.currentTime = time;
	}

	public static void main(String[] args) throws InterruptedException {

		RandomSensorNumber sensorNumber = new RandomSensorNumber();
		ACEventHandler acEventHandler = new ACEventHandler();
		HeatherEventHandler heatherEventHandler = new HeatherEventHandler();

		AirConditionerModel airConditioner = new AirConditionerModel();
		IAirConditioner iAirConditioner = new AirConditionerController(airConditioner);

		HeatherModel heatherModel = new HeatherModel();
		IHeather iHeather = new HeatherController(heatherModel);

		IWindow iWindow = new WindowModel();
		WindowController windowController = new WindowController(iWindow);

		acEventHandler.events.subscribe("highTemperatureDetected", new HighTemperatureDetectedListener());
		acEventHandler.events.subscribe("lowTemperatureDetected", new LowTemperatureDetactedListener());
		acEventHandler.events.subscribe("normalTemperatureDetected", new NormalTemperatureDetactedListener());

		heatherEventHandler.events.subscribe("coldWeatherDetected", new ColdWeatherDetactedListener());
		heatherEventHandler.events.subscribe("hotWeatherDetected", new HotWeatherDetactedListener());

		do {
			TimeUnit.SECONDS.sleep(1);

			if (!isManual) {
				sensorNumber.getSensorRandomizeNumber();

				currentTemperatureNumber = sensorNumber.getTemperatureNumber();
				currentAnemometerNumber = sensorNumber.getAnemoNumber();
				currentRainingStatus = sensorNumber.getRainDropSensorStatus();
				currentTime = sensorNumber.getTime();
			}

			if (currentTemperatureNumber >= 27) {
				acEventHandler.highTemperatureDetected(currentTemperatureNumber, iAirConditioner);
				heatherEventHandler.hotWeatherDetected(iHeather);
			}

			if (currentTemperatureNumber >= 15 &&
					currentTemperatureNumber < 27) {
				acEventHandler.normalTemperatureDetected(currentTemperatureNumber, iAirConditioner);
				heatherEventHandler.hotWeatherDetected(iHeather);
			}

			if (currentTemperatureNumber < 15) {
				acEventHandler.lowTemperatureDetected(currentTemperatureNumber, iAirConditioner);
				heatherEventHandler.coldWeatherDetected(iHeather);
			}

			if (currentRainingStatus) {
				isRaining = true;
			} else {
				isRaining = false;
			}

			if (currentAnemometerNumber > 15) {
				isHeavyWind = true;
			} else {
				isHeavyWind = false;
			}

			if (currentTime.isBefore(LocalTime.parse("18:00:00"))
					&& currentTime.isAfter(LocalTime.parse("06:00:00"))) {
				isNight = false;
			} else {
				isNight = true;
			}

			windowController.setWindowController(isRaining, isHeavyWind, isNight);

		} while (sensorActive);
	}
}
