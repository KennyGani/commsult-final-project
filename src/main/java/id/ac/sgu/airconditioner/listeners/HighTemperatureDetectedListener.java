package id.ac.sgu.airconditioner.listeners;

import id.ac.sgu.airconditioner.acinterface.IAirConditioner;
import id.ac.sgu.airconditioner.enums.AirConditionerPowerEnum;

public class HighTemperatureDetectedListener implements ACEventListener {

    AirConditionerPowerEnum airConditionerPower;

    @Override
    public void update(String eventType, double temperature, IAirConditioner airConditioner) {
        if (temperature >= 27 && temperature < 30) {
            airConditionerPower = AirConditionerPowerEnum.MEDIUM;
        }
        if (temperature >= 30) {
            airConditionerPower = AirConditionerPowerEnum.HIGH;
        }

        airConditioner.setAirConditionerStatus(true, airConditionerPower);
    }
}