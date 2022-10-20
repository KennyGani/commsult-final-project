package id.ac.sgu;

import java.time.LocalTime;

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
	private static SystemController instance = new SystemController();

	private static boolean sensorActive = true;
	private static boolean isManual = false;
	private static boolean isRaining;
	private static boolean isHeavyWind;
	private static boolean isNight;

	private static double currentTemperatureNumber;
	private static double currentAnemometerNumber;
	private static boolean currentRainingStatus;
	private static LocalTime currentTime;

	private static RandomSensorNumber sensorNumber = new RandomSensorNumber();
	private static ACEventHandler acEventHandler = new ACEventHandler();
	private static HeatherEventHandler heatherEventHandler = new HeatherEventHandler();

	private static AirConditionerModel airConditioner = new AirConditionerModel();
	private static IAirConditioner iAirConditioner = new AirConditionerController(airConditioner);

	private static HeatherModel heatherModel = new HeatherModel();
	private static IHeather iHeather = new HeatherController(heatherModel);

	private static IWindow iWindow = new WindowModel();
	private static WindowController windowController = new WindowController(iWindow);

	private SystemController() {
	}

	public static SystemController getInstance() {
		return instance;
	}

	public void setManualTemperature(double temperatureNumber) {
		SystemController.currentTemperatureNumber = temperatureNumber;
	}

	public double getManualTemperature() {
		return currentTemperatureNumber;
	}

	public void setManualAnemometer(double anemometerNumber) {
		SystemController.currentAnemometerNumber = anemometerNumber;
	}

	public double getManualAnemometer() {
		return currentAnemometerNumber;
	}

	public void setManualRainingStatus(Boolean rainingStatus) {
		SystemController.currentRainingStatus = rainingStatus;
	}

	public void setCurrentTme(LocalTime time) {
		SystemController.currentTime = time;
	}

	public LocalTime getCurrentTime() {
		return currentTime;
	}

	public void setIsManual(boolean status) {
		SystemController.isManual = status;
	}

	public AirConditionerModel getAirConditioner() {
		return airConditioner;
	}

	public HeatherModel getHeather() {
		return heatherModel;
	}

	public IWindow getWindow() {
		return iWindow;
	}

	public RandomSensorNumber getSensorNumber() {
		return sensorNumber;
	}

	public void run() throws InterruptedException {
		acEventHandler.events.subscribe("highTemperatureDetected", new HighTemperatureDetectedListener());
		acEventHandler.events.subscribe("lowTemperatureDetected", new LowTemperatureDetactedListener());
		acEventHandler.events.subscribe("normalTemperatureDetected", new NormalTemperatureDetactedListener());

		heatherEventHandler.events.subscribe("coldWeatherDetected", new ColdWeatherDetactedListener());
		heatherEventHandler.events.subscribe("hotWeatherDetected", new HotWeatherDetactedListener());

		Thread thread = new Thread(() -> {
			while (sensorActive) {
				try {
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

					if (currentTime.isAfter(LocalTime.parse("18:00:00"))
							|| currentTime.isBefore(LocalTime.parse("06:00:00"))) {
						isNight = true;
					} else {
						isNight = false;
					}

					windowController.setWindowController(isRaining, isHeavyWind, isNight);
					if (isManual) {
						Thread.sleep(10);
					} else {
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		thread.start();
	}
}
