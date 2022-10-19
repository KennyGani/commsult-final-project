package id.ac.sgu.airconditioner.listeners;

import id.ac.sgu.airconditioner.AirConditioner;
import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;

public class HighTemperatureDetectedListener implements EventListener {

    AirConditionerPowerEnum airConditionerPower;

    @Override
    public void update(String eventType, double temperature, AirConditioner airConditioner) {
        if (temperature >= 27 && temperature < 30) {
            airConditionerPower = AirConditionerPowerEnum.MEDIUM;
        }
        if (temperature >= 30) {
            airConditionerPower = AirConditionerPowerEnum.HIGH;
        }

        airConditioner.setAirConditionerStatus(true, airConditionerPower);
    }
}